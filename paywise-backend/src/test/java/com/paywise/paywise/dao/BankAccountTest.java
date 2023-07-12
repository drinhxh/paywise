package com.paywise.paywise.dao;

import com.paywise.paywise.PaywiseApplication;
import com.paywise.paywise.entity.BankAccount;
import com.paywise.paywise.entity.User;
import com.paywise.paywise.exception.BankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PaywiseApplication.class)
@AutoConfigureTestEntityManager
public class BankAccountTest {

  private UserDAO userDAO;

  @Autowired
  public BankAccountTest(UserDAO userDAO){
    this.userDAO = userDAO;
  }

  @Test
  public void shouldThrowBankExceptionForBankWithoutNumber(){
    User userWithoutBankNumber = new User();
    BankAccount bankWithoutAccountNumber = new BankAccount("", "Spar", 250.00);

    assertThrows(BankException.class, () -> {
      userWithoutBankNumber.setBankAccount(bankWithoutAccountNumber);
    });
  }

  @Test
  public void shouldThrowBankExceptionForBankWithoutName(){
    User userWithoutBankName = new User();
    BankAccount bankAccountWithoutName = new BankAccount("AT11 22 33", "", 550.00);

    assertThrows(BankException.class, () -> {
      userWithoutBankName.setBankAccount(bankAccountWithoutName);
    });
  }

  @Test
  public void shouldThrowBankExceptionForLowBalance(){
    User userWithLowBalance = new User();
    BankAccount bankAccountWithLowBalance = new BankAccount("AT 11 22 33", "Procredit", -150.00);

    assertThrows(BankException.class, () -> {
      userWithLowBalance.setBankAccount(bankAccountWithLowBalance);
    });
  }

  @Test
  public void shouldNotThrowAnyExceptionBankAccount(){
    User userWithValidBankAccount = new User();
    BankAccount validBankAccount = new BankAccount("AT11 22 33 44", "Reifeissen Bank", 800.00);

    Assertions.assertDoesNotThrow(() -> {
      userWithValidBankAccount.setBankAccount(validBankAccount);
    });
  }

}
