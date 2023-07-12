package com.paywise.paywise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fund_transfer")
public class FundTransfer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "source_account_number")
  private String sourceAccountNumber;

  @Column(name = "destination_account_number")
  private String destinationAccountNumber;

  @Column(name = "date_time")
  private String dateTime;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_sender_id")
  private User user_sender;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_receiver_id")
  private User user_receiver;

  public FundTransfer(){

  }

  public FundTransfer(Double amount, String sourceAccountNumber, String destinationAccountNumber, String dateTime) {
    this.amount = amount;
    this.sourceAccountNumber = sourceAccountNumber;
    this.destinationAccountNumber = destinationAccountNumber;
    this.dateTime = dateTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getSourceAccountNumber() {
    return sourceAccountNumber;
  }

  public void setSourceAccountNumber(String sourceAccountNumber) {
    this.sourceAccountNumber = sourceAccountNumber;
  }

  public String getDestinationAccountNumber() {
    return destinationAccountNumber;
  }

  public void setDestinationAccountNumber(String destinationAccountNumber) {
    this.destinationAccountNumber = destinationAccountNumber;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public User getUser_sender() {
    return user_sender;
  }

  public void setUser_sender(User user_sender) {
    this.user_sender = user_sender;
  }

  public User getUser_receiver() {
    return user_receiver;
  }

  public void setUser_receiver(User user_receiver) {
    this.user_receiver = user_receiver;
  }


  @Override
  public String toString() {
    return "FundTransfer{" +
            "id=" + id +
            ", amount=" + amount +
            ", sourceAccountNumber='" + sourceAccountNumber + '\'' +
            ", destinationAccountNumber='" + destinationAccountNumber + '\'' +
            ", dateTime='" + dateTime + '\'' +
            ", user_sender=" + (user_sender != null ? user_sender.getId() : null) +
            ", user_receiver=" + (user_receiver != null ? user_receiver.getId() : null) +
            '}';
  }
}
