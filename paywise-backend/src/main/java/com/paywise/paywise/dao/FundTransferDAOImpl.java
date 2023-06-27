package com.paywise.paywise.dao;

import com.paywise.paywise.entity.FundTransfer;
import com.paywise.paywise.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class FundTransferDAOImpl implements FundTransferDAO{
  private final EntityManager entityManager;
  private final UserDAO userDAO;

  @Autowired
  public FundTransferDAOImpl(EntityManager entityManager, UserDAO userDAO) {
    this.entityManager = entityManager;
    this.userDAO = userDAO;
  }

  @Override
  @Transactional
  public void saveFundTransfer(FundTransfer fundTransfer) {
    entityManager.persist(fundTransfer);
  }

  @Override
  @Transactional
  public void updateFundTransfer(FundTransfer fundTransfer) {
    entityManager.merge(fundTransfer);
  }

  @Override
  public List<FundTransfer> findAllFundTransfers() {
    TypedQuery<FundTransfer> query = entityManager.createQuery("FROM FundTransfer", FundTransfer.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public FundTransfer transferFunds(int senderId, int receiverId, double amount) {
    User sender = userDAO.findUserById(senderId);
    User receiver = userDAO.findUserById(receiverId);

    // small error check
    if(sender == null || receiver == null){
      throw new IllegalArgumentException("Invalid sender or receiver!");
    }

    double senderBalance = sender.getBankAccount().getBalance();

    if(senderBalance < amount){
      throw new IllegalArgumentException("Insufficient balance. You can not move forward with the transfer!");
    }

    double updatedSenderBalance = senderBalance - amount;
    userDAO.updateUserBalance(senderId, updatedSenderBalance);

    double updatedReceiverBalance = receiver.getBankAccount().getBalance() + amount;
    userDAO.updateUserBalance(receiverId, updatedReceiverBalance);

    FundTransfer fundTransfer = new FundTransfer();

    fundTransfer.setAmount(amount);
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = currentDateTime.format(formatter);
    fundTransfer.setDateTime(formattedDateTime);
    fundTransfer.setDestinationAccountNumber(receiver.getBankAccount().getAccountNumber());
    fundTransfer.setSourceAccountNumber(sender.getBankAccount().getAccountNumber());
    saveFundTransfer(fundTransfer);

    // Sender update transfer
    sender.addSenderFundTransfer(fundTransfer);
    userDAO.updateSenderFundTransfer(senderId, fundTransfer);

    FundTransfer receiverFundTransfer = new FundTransfer();
    receiverFundTransfer.setAmount(amount);
    receiverFundTransfer.setDateTime(formattedDateTime);
    receiverFundTransfer.setDestinationAccountNumber("NONE");
    receiverFundTransfer.setSourceAccountNumber(sender.getBankAccount().getAccountNumber());

    receiver.addReceiverFundTransfer(receiverFundTransfer);
    userDAO.updateReceiverFundTransfer(receiverId, receiverFundTransfer);

    return fundTransfer;
  }
}
