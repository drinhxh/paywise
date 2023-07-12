package com.paywise.paywise.dao;

import com.paywise.paywise.PaywiseApplication;
import com.paywise.paywise.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaywiseApplication.class)
@AutoConfigureTestEntityManager
class UserDAOImplTest {

  private UserDAO userDAO;

  @Autowired
  public UserDAOImplTest(UserDAO userDAO){
    this.userDAO = userDAO;
  }

  @Test
  public void shouldReturnUsernameError() {
    boolean invalidUsername = userDAO.validateUsername("melo"); // melo is already taken as username

    assertTrue(invalidUsername);
  }

  @Test
  public void shouldReturnBankAccountError(){
    boolean invalidBankAccount = userDAO.validateBank("AT52 8888");

    assertTrue(invalidBankAccount);
  }

  @Test
  public void shouldReturnCardError(){
    boolean invalidCardNumber = userDAO.validateCard("1111 2222 3333 4444");

    assertTrue(invalidCardNumber);
  }

  @Test
  public void shouldThrowNullPointerExceptionForEmptyUser(){
    User emptyUser = new User();

    assertThrows(NullPointerException.class, () -> {
      userDAO.saveUser(emptyUser);
    });
  }

  @Test
  public void shouldReturnNullForNullUser(){
    User nullUser = null;

    assertNull(userDAO.saveUser(nullUser));
  }

  @Test
  public void shouldAcceptUsername(){
    boolean validUsername = userDAO.validateUsername("springBoot");

    assertFalse(validUsername);
  }

  @Test
  public void shouldAcceptBankAccount(){
    boolean validBankAccount = userDAO.validateBank("AT44 1234 5678 0000");

    assertFalse(validBankAccount);
  }

  @Test
  public void shouldAcceptCard(){
    boolean validCardNumber = userDAO.validateCard("8888 6666 4444 2222");

    assertFalse(validCardNumber);
  }

}