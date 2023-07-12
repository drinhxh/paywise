package com.paywise.paywise.dao;

import com.paywise.paywise.PaywiseApplication;
import com.paywise.paywise.entity.Card;
import com.paywise.paywise.entity.User;
import com.paywise.paywise.exception.CardException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PaywiseApplication.class)
@AutoConfigureTestEntityManager
public class CardTest {

  @Test
  public void shouldThrowCardExceptionForCardWithoutNumber(){
    User userWithoutCardNumber = new User();
    Card cardWithoutNumber = new Card("", "Card No Number", "11/25", "222");

    assertThrows(CardException.class, () -> {
      userWithoutCardNumber.setCard(cardWithoutNumber);
    });
  }

  @Test
  public void shouldThrowCardExceptionForCardWithoutHolder(){
    User userWithoutCardHolder = new User();
    Card cardWithoutHolder = new Card("1122 3344 5566 7788", "", "11/25", "222");

    assertThrows(CardException.class, () -> {
      userWithoutCardHolder.setCard(cardWithoutHolder);
    });
  }

  @Test
  public void shouldThrowCardExceptionForCardWithoutExpiration(){
    User userWithoutCardExpiration = new User();
    Card cardWithoutExpiration = new Card("1122 3344 5566 7788", "Card Test", "", "222");

    assertThrows(CardException.class, () -> {
      userWithoutCardExpiration.setCard(cardWithoutExpiration);
    });
  }

  @Test
  public void shouldThrowCardExceptionForCardWithoutSecurityCode(){
    User userWithoutCardSecurityCode= new User();
    Card cardWithoutSecurityCode = new Card("1122 3344 5566 7788", "Card Test", "11/25", "");

    assertThrows(CardException.class, () -> {
      userWithoutCardSecurityCode.setCard(cardWithoutSecurityCode);
    });
  }


}

















