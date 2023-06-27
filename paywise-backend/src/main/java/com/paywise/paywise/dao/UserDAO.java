package com.paywise.paywise.dao;

import com.paywise.paywise.entity.FundTransfer;
import com.paywise.paywise.entity.Roles;
import com.paywise.paywise.entity.User;

import java.util.List;

public interface UserDAO{

  User saveUser(User user);
  User updateUser(User user);
  List<User> getAllUsers();
  User findUserById(Integer id);
  void deleteUserById(Integer id);
  void updateUserBalance(int id, double balance);
  void updateSenderFundTransfer(int id, FundTransfer fundTransfer);
  void updateReceiverFundTransfer(int id, FundTransfer fundTransfer);
  void updateRoles(int id, Roles roles);


  // TODO : getRoles(), getFundTransfers()
  // TODO : implement on RolesDAO/FundTransferDAO, import on UserDAO and get them as getters



  // ENCRYPTION
  String encodeBCryptEncode(String password);

}
