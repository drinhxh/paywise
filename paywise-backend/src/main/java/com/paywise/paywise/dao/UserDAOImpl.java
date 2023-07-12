package com.paywise.paywise.dao;

import com.paywise.paywise.constants.Constants;
import com.paywise.paywise.entity.*;
import com.paywise.paywise.exception.UserNotFoundException;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

  private final EntityManager entityManager;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public User saveUser(User user) {

    if(user == null){
      System.out.println("Can not save empty user!");
      return null;
    }

    if(validateUsername(user.getUsername())){
      System.out.println("[ERROR: USERNAME] This username is already taken. please user another one!");
      return null;
    }

    if(validateBank(user.getBankAccount().getAccountNumber())){
      System.out.println("[ERROR: BANK ACCOUNT] This bank account is already in use. Please use another one!");
      return null;
    }

    if(validateCard(user.getCard().getCardNumber())){
      System.out.println("[ERROR: CARD NUMBER] This card number is already in use. Please user another one!");
      return null;
    }

    Roles newUserRole = new Roles(Constants.SIMPLE_USER); // default setter: "USER"
    user.addRole(newUserRole);

    // encode password before saving
    // String encodedPassword = Constants.BCRYPT + encodeBCryptEncode(user.getPassword());
    // user.setPassword(encodedPassword);

    String noopPassword = Constants.NO_OP + user.getPassword();
    user.setPassword(noopPassword);

    entityManager.persist(user);
    System.out.println("[CREATED USER] New user with id: " + user.getId());
    System.out.println("[CREATED USER] User: " + user);

    return user;
  }

  @Override
  @Transactional
  public User updateUser(User user) {
    if(user == null){
      return null;
    }

    System.out.println("[UPDATED USER] User with id: " + user.getId());
    System.out.println("[UPDATED USER] User: " + user);

    entityManager.merge(user);

    return user;
  }

  @Override
  public List<User> getAllUsers() {
    TypedQuery<User> query = entityManager.createQuery("FROM User ", User.class);
    System.out.println("[ALL USERS] Found all users.");
    return query.getResultList();
  }

  @Override
  public User findUserById(Integer id) {
    User foundUser = entityManager.find(User.class, id);

    if(foundUser == null){
      throw new UserNotFoundException("User with ID: " + id + " was not found!");
    }

    System.out.println("[FOUND USER] User found with ID: " + foundUser.getId());
    System.out.println("[FOUND USER] User: " + foundUser);
    return foundUser;
  }

  @Override
  public User findUserByUsername(String username) {
    String queryString = "SELECT u FROM User u WHERE u.username = :username";
    TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
    query.setParameter("username", username);

    List<User> users = query.getResultList();

    if(users.isEmpty()){
      throw new UserNotFoundException("User with USERNAME: " + username + " was not found!");
    }

    User foundUser = users.get(0);

    System.out.println("[FOUND USER] User found with USERNAME: " + foundUser.getUsername());
    System.out.println("[FOUND USER] User: " + foundUser);

    return foundUser;
  }

  @Override
  @Transactional
  public void deleteUserById(Integer id) {
    User userToDelete = entityManager.find(User.class, id);

    System.out.println("[DELETED] User with ID: " + userToDelete.getId());
    System.out.println("[DELETED] User: " + userToDelete);

    entityManager.remove(userToDelete);
  }

  @Override
  @Transactional
  public void updateUserBalance(int id, double balance) {
    User user = entityManager.find(User.class, id);
    user.getBankAccount().setBalance(balance);

    System.out.println("[UPDATED BALANCE] user with ID: " + user.getId());
    System.out.println("[UPDATED BALANCE]" + user);

    entityManager.merge(user);
  }

  @Override
  @Transactional
  public void updateSenderFundTransfer(int id, FundTransfer fundTransfer) {
    User sender = entityManager.find(User.class, id);
    sender.addSenderFundTransfer(fundTransfer);

    System.out.println("[UPDATED TRANSFER] Updated user Fund Transfer.");
    System.out.println("[UPDATED TRANSFER] User SEND    FundTransfer: " + sender.getSenderFundTransfers());

    entityManager.merge(sender);
  }

  @Override
  @Transactional
  public void updateReceiverFundTransfer(int id, FundTransfer fundTransfer) {
    User receiver = entityManager.find(User.class, id);
    receiver.addReceiverFundTransfer(fundTransfer);

    System.out.println("[UPDATED TRANSFER] Updated user Fund Transfer.");
    System.out.println("[UPDATED TRANSFER] User SEND    FundTransfer: " + receiver.getReceiverFundTransfers());

    entityManager.merge(receiver);
  }

  @Override
  public void updateRoles(int id, Roles roles) {
    User user = entityManager.find(User.class, id);
    if(user == null){
      System.out.println("NULL FROM UPDATE ROLE");
    }
    user.addRole(roles);

    System.out.println("[UPDATED ROLE] User got Role updated.");
    System.out.println("[UPDATED ROLE] User Role: " + user.getRoles());

    entityManager.merge(user);
  }

  @Override
  public String encodeBCryptEncode(String password) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println("[PASSWORD ENCODED] User password encoded BCRYPT.");
    return bCryptPasswordEncoder.encode(password);
  }

  @Override
  public List<FundTransfer> getSenderFundTransfers(Integer id) {
    User user = entityManager.find(User.class, id);

    return user.getSenderFundTransfers();
  }

  @Override
  public List<FundTransfer> getReceiverFundTransfers(Integer id) {
    User user = entityManager.find(User.class, id);

    return user.getReceiverFundTransfers();
  }

  @Override
  public User getUserByBankAccount(String bankAcc) {
    String queryString = "SELECT u FROM User u WHERE u.bankAccount.accountNumber = :bankAcc";
    TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
    query.setParameter("bankAcc", bankAcc);

    List<User> users = query.getResultList();

    if(users.isEmpty()){
      throw new UserNotFoundException("User with USERNAME: " + bankAcc + " was not found!");
    }

    User foundUser = users.get(0);

    System.out.println("[FOUND USER] User found with USERNAME: " + foundUser.getUsername());
    System.out.println("[FOUND USER] User: " + foundUser);

    return foundUser;
  }

  @Override
  public boolean validateUsername(String username) {
    String queryString = "SELECT u.username FROM User u";
    TypedQuery<String> query = entityManager.createQuery(queryString, String.class);

    List<String> usernames = query.getResultList();

    return usernames.contains(username);
  }

  @Override
  public boolean validateBank(String bankAccount) {
    String queryString = "SELECT u.bankAccount.accountNumber FROM User u";
    TypedQuery<String> query = entityManager.createQuery(queryString, String.class);

    List<String> bankAccounts = query.getResultList();

    return bankAccounts.contains(bankAccount);
  }

  @Override
  public boolean validateCard(String cardNumber) {
    String queryString = "SELECT u.card.cardNumber FROM User u";
    TypedQuery<String> query = entityManager.createQuery(queryString, String.class);

    List<String> cards = query.getResultList();

    return cards.contains(cardNumber);
  }
}






