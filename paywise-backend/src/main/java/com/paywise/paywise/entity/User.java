package com.paywise.paywise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;

  @Column(name = "user_name")
  private String username;
  @Column(name = "password")
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "bank_account_id")
  private BankAccount bankAccount;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "card_id")
  private Card card;

  @JsonIgnore
  @OneToMany(mappedBy = "user_sender",
             cascade = CascadeType.ALL,  // TODO : all?
             fetch = FetchType.EAGER)
  private List<FundTransfer> senderFundTransfers;

  @JsonIgnore
  @OneToMany(mappedBy = "user_receiver",
             cascade = CascadeType.ALL,  // TODO : all?
             fetch = FetchType.EAGER)
  private List<FundTransfer> receiverFundTransfers;

  @JsonIgnore
  @OneToMany(mappedBy = "user_role",
             cascade = CascadeType.ALL,  // TODO : all?
             fetch = FetchType.EAGER)
  private List<Roles> roles;

  public User(){

  }

  public User(String firstName, String lastName, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public List<FundTransfer> getSenderFundTransfers() {
    return senderFundTransfers;
  }

  public void setSenderFundTransfers(List<FundTransfer> senderFundTransfers) {
    this.senderFundTransfers = senderFundTransfers;
  }

  public List<FundTransfer> getReceiverFundTransfers() {
    return receiverFundTransfers;
  }

  public void setReceiverFundTransfers(List<FundTransfer> receiverFundTransfers) {
    this.receiverFundTransfers = receiverFundTransfers;
  }


  public void addSenderFundTransfer(FundTransfer fundTransfer){
    if(senderFundTransfers == null){
      senderFundTransfers = new ArrayList<>();
    }

    senderFundTransfers.add(fundTransfer);
    fundTransfer.setUser_sender(this);
  }

  public void addReceiverFundTransfer(FundTransfer fundTransfer){
    if(receiverFundTransfers == null){
      receiverFundTransfers = new ArrayList<>();
    }

    receiverFundTransfers.add(fundTransfer);
    fundTransfer.setUser_receiver(this);
  }

  public void addRole(Roles role){
    if(roles == null){
      roles = new ArrayList<>();
    }

    if(role == null){
      System.out.println("NULL FROM ADD ROLE IN USER");
      throw new IllegalArgumentException("Invalid role!");
    }

    roles.add(role);
    role.setUserRole(this); /// TODO : assert
  }

  public List<Roles> getRoles() {
    return roles;
  }

  public void setRoles(List<Roles> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", bankAccount=" + bankAccount +
            ", card=" + card +
            ", senderFundTransfers=" + senderFundTransfers +
            ", receiverFundTransfers=" + receiverFundTransfers +
            ", roles=" + roles +
            '}';
  }
}
