package com.learn.service;

import com.learn.model.User;
import com.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> retrieveUsers() {
    try {
      return userRepository.findAll();
    } catch (Exception e) {
      throw new RuntimeException("RetrieveUsersException", e.getCause());
    }
  }

  public User createNewUser(User user) {
    try {
      user.setRegisteredAt(ZonedDateTime.now());
      user.setLastLogin(ZonedDateTime.now());
      return userRepository.save(user);
    } catch (Exception e) {
      throw new RuntimeException("CreateNewUserException", e.getCause());
    }
  }
}
