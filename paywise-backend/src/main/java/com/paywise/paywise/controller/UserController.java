package com.paywise.paywise.controller;

import com.paywise.paywise.dao.UserDAO;
import com.paywise.paywise.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {
  private final UserDAO userDAO;

  public UserController(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @GetMapping("/hello")
  public String hello(){
    return "HELLO FROM /home/hello";
  }

  @PostMapping("/add")
  public ResponseEntity<User> addUser(@RequestBody User user){
    User newUser = userDAO.saveUser(user);

    if(newUser == null){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @GetMapping("/users/find/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
    User user = userDAO.findUserById(id);

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
