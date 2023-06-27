package com.paywise.paywise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "card_holder")
  private String cardHolder;

  @Column(name = "expiration")
  private String expiration;

  @Column(name = "security_code")
  private String securityCode;

  @JsonIgnore
  @OneToOne(mappedBy = "card",
            cascade = CascadeType.ALL)
  private User user;

  public Card(){

  }

  public Card(String cardNumber, String cardHolder, String expiration, String securityCode) {
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expiration = expiration;
    this.securityCode = securityCode;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public String getExpiration() {
    return expiration;
  }

  public void setExpiration(String expiration) {
    this.expiration = expiration;
  }

  public String getSecurityCode() {
    return securityCode;
  }

  public void setSecurityCode(String securityCode) {
    this.securityCode = securityCode;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Card{" +
            "id=" + id +
            ", cardNumber='" + cardNumber + '\'' +
            ", cardHolder='" + cardHolder + '\'' +
            ", expiration='" + expiration + '\'' +
            ", securityCode='" + securityCode + '\'' +
            ", user=" + (user != null ? user.getId() : null) +
            '}';
  }
}
