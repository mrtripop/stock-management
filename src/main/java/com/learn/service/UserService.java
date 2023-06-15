package com.learn.service;

import com.learn.model.User;
import com.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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

  public User retrieveUserById(Long id) {
    try {
      Optional<User> user = this.userRepository.findById(id);
      return user.orElse(null);
    } catch (Exception e) {
      throw new RuntimeException("RetrieveUserByIdException", e.getCause());
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

  public User updateUserGeneralInfo(Long id, User userUpdate) {
    try {
      Optional<User> user = userRepository.findById(id);
      if (user.isPresent()) {
        User originalUser = user.get();
        originalUser.setRoleId(userUpdate.getRoleId());
        originalUser.setFirstName(userUpdate.getFirstName());
        originalUser.setLastName(userUpdate.getLastName());
        originalUser.setUsername(userUpdate.getUsername());
        originalUser.setMobile(userUpdate.getMobile());
        originalUser.setEmail(userUpdate.getEmail());
        originalUser.setIntro(userUpdate.getIntro());
        originalUser.setProfile(userUpdate.getProfile());
        userRepository.save(originalUser);
        return originalUser;
      }
      return null;
    } catch (Exception e) {
      throw new RuntimeException("UpdateUserById", e.getCause());
    }
  }

  public boolean deleteUserById(Long id) {
    try {
      Optional<User> originalUser = userRepository.findById(id);
      if (originalUser.isPresent()) {
        userRepository.deleteById(id);
        return true;
      }
      return false;
    } catch (Exception e) {
      throw new RuntimeException("DeleteUserById", e.getCause());
    }
  }
}
