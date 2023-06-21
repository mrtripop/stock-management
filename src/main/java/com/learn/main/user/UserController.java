package com.learn.main.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
  public ResponseEntity<List<User>> retrieveUsers(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "asc") String orderBy) {
    try {
      List<User> result = userService.retrieveUsers(page, size, orderBy);
      log.debug(result.toString());
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> retrieveUserById(@PathVariable Long id) {
    try {
      User user = userService.retrieveUserById(id);
      if (user == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(user);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<User> createNewUser(@RequestBody User user) {
    try {
      User result = userService.createNewUser(user);
      return ResponseEntity.created(URI.create("http://localhost:8080")).body(result);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }

  @PutMapping("/{id}/general")
  public ResponseEntity<User> updateUserGeneralInfo(@PathVariable Long id, @RequestBody User user) {
    try {
      User updateUser = userService.updateUserGeneralInfo(id, user);
      return ResponseEntity.accepted().body(updateUser);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
    try {
      boolean status = userService.deleteUserById(id);
      if (status) {
        return ResponseEntity.accepted().body("Delete user id: " + id + " success!");
      }
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }
}