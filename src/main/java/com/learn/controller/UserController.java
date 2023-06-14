package com.learn.controller;

import com.learn.model.User;
import com.learn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/users")
@Slf4j
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<User>> retrieveUsers() {
    try {
      List<User> result = userService.retrieveUsers();
      log.debug(result.toString());
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<User> createNewUser(@RequestBody User user) {
    try {
      User result = userService.createNewUser(user);
      log.debug(result.toString());
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }
}
