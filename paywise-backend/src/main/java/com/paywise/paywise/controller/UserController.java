package com.paywise.paywise.controller;

import com.paywise.paywise.dao.FundTransferDAO;
import com.paywise.paywise.dao.UserDAO;
import com.paywise.paywise.entity.FundTransfer;
import com.paywise.paywise.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/home")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
  private final UserDAO userDAO;
  private final FundTransferDAO fundTransferDAO;

  public UserController(UserDAO userDAO, FundTransferDAO fundTransferDAO) {
    this.userDAO = userDAO;
    this.fundTransferDAO = fundTransferDAO;
  }

  @GetMapping("/users/sender/{id}")
  public ResponseEntity<List<FundTransfer>> getSenderFundTransfers(@PathVariable("id") Integer id) {
    List<FundTransfer> senderTransfers = userDAO.getSenderFundTransfers(id);

    if (senderTransfers.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(senderTransfers, HttpStatus.OK);
  }

  @GetMapping("/users/bank/acc/find/{bankAcc}")
  public ResponseEntity<User> getUserByBankAccount(@PathVariable("bankAcc") String bankAcc){
    User userByBankAcc = userDAO.getUserByBankAccount(bankAcc);

    if (userByBankAcc == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(userByBankAcc, HttpStatus.OK);
  }

  @GetMapping("/users/receiver/{id}")
  public ResponseEntity<List<FundTransfer>> getReceiverFundTransfers(@PathVariable("id") Integer id) {
    List<FundTransfer> receiverTransfers = userDAO.getReceiverFundTransfers(id);

    if (receiverTransfers.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(receiverTransfers, HttpStatus.OK);
  }

  @PostMapping("/users/login") // TODO : not working, keep getting 403 even though did .permitAll() on security
  public ResponseEntity<User> loginUser(@RequestBody User user) {
    String enteredPassword = user.getPassword();

    // Retrieve the user from the database based on the username
    User retrievedUser = userDAO.findUserByUsername(user.getUsername());

    if (retrievedUser != null) {
      // Compare the entered password with the bcrypt-encrypted password
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      boolean passwordsMatch = passwordEncoder.matches(enteredPassword, retrievedUser.getPassword());

      if (passwordsMatch) {
        // Passwords match, login is successful
        return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
      } else {
        // Password is incorrect
        return new ResponseEntity<>(retrievedUser, HttpStatus.UNAUTHORIZED);
      }
    } else {
      // User not found
      return new ResponseEntity<>(retrievedUser, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/transfer/{senderId}/{receiverUsername}/{amount}")
  public ResponseEntity<User> transferMoney(@PathVariable("senderId") String senderUsername,
                                            @PathVariable("receiverUsername") String receiverUsername,
                                            @PathVariable("amount") Double amount){
    User sender = userDAO.findUserByUsername(senderUsername);
    User receiver = userDAO.findUserByUsername(receiverUsername);

    if(sender == null || receiver == null){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    System.out.println("Transferring the amount of: " + amount);
    FundTransfer fundTransfer = fundTransferDAO.transferFunds(sender.getId(), receiver.getId(), amount);
    System.out.println("Transfer completed!");

    System.out.println("Setting up SENDER transfer...");
    sender.addSenderFundTransfer(fundTransfer);
    System.out.println("SENDER transfer done!");

    System.out.println("Setting up RECEIVER transfer...");
    receiver.addReceiverFundTransfer(fundTransfer);
    System.out.println("RECEIVER transfer done!");

    return new ResponseEntity<>(sender, HttpStatus.OK);
  }


  @PostMapping("/add")
  public ResponseEntity<User> addUser(@RequestBody User user){
    User newUser = userDAO.saveUser(user);

    if(newUser == null){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @GetMapping("/users/user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
    User user = userDAO.findUserById(id);

    if(user == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK); // TODO : HttpStatus.FOUND ?
  }

  @GetMapping("/users/find/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
    User user = userDAO.findUserByUsername(username);

    if(user == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
    User user = userDAO.findUserById(id);

    if(user == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    userDAO.deleteUserById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userDAO.getAllUsers();

    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateUser(@RequestBody User user){
    User updatedUser = userDAO.updateUser(user);

    if(updatedUser == null){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

}
