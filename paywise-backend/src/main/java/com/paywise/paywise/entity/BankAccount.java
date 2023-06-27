package com.paywise.paywise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "account_number")
  private String accountNumber;

  @Column(name = "balance")
  private Double balance;

  @JsonIgnore
  @OneToOne(mappedBy = "bankAccount",
            cascade = CascadeType.ALL) // ALL ?
  private User user; // FK ??

  public BankAccount(){

  }

  public BankAccount(String accountNumber, Double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "BankAccount{" +
            "id=" + id +
            ", accountNumber='" + accountNumber + '\'' +
            ", balance=" + balance +
            ", user=" + (user != null ? user.getId() : null) +
            '}';
  }
}
