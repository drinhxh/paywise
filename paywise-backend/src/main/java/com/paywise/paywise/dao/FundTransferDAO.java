package com.paywise.paywise.dao;

import com.paywise.paywise.entity.FundTransfer;

import java.util.List;

public interface FundTransferDAO {
  void saveFundTransfer(FundTransfer fundTransfer);
  void updateFundTransfer(FundTransfer fundTransfer);
  List<FundTransfer> findAllFundTransfers();
  FundTransfer transferFunds(int senderId, int receiverId, double amount);
}
