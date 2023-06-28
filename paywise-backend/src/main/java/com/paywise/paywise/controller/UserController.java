package com.paywise.paywise.controller;

import com.paywise.paywise.dao.UserDAO;
import com.paywise.paywise.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
  private final UserDAO userDAO;

  public UserController(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @PostMapping("/users/login")
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
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        // Password is incorrect
        return new ResponseEntity<>(retrievedUser, HttpStatus.UNAUTHORIZED);
      }
    } else {
      // User not found
      return new ResponseEntity<>(retrievedUser, HttpStatus.NOT_FOUND);
    }
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
