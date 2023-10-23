package com.gbtelles.todobackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbtelles.todobackend.DTO.UserEntityDTO;
import com.gbtelles.todobackend.Entity.UserEntity;
import com.gbtelles.todobackend.Repository.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public List<UserEntity> getAll() {
    return userRepository.findAll();
  }

  public UserEntity getById(long id) {
    return userRepository.findById(id).orElse(null);
  }

  public UserEntity saveUserEntity(UserEntityDTO userEntity) {
    UserEntity newUser = new UserEntity();

    newUser.setUserName(userEntity.getUserName());
    newUser.setUserEmail(userEntity.getUserEmail());
    newUser.setUserPassword(userEntity.getUserPassword());
    // newUser.setToDos(new HashSet<>());

    return userRepository.save(newUser);
  }

  public UserEntity updateUserEntity(long id, UserEntity user) {
    UserEntity updatedUser = userRepository.findById(id).orElse(null);
    
    if (updatedUser != null) {
      updatedUser.setUserName(user.getUserName());
      updatedUser.setUserEmail(user.getUserEmail());
      updatedUser.setUserPassword(user.getUserPassword());
      updatedUser.setToDos(user.getToDos());

      return userRepository.save(updatedUser);
    } else {
      return null;
    }
  }

  public boolean deleteUserEntity(long id) {
    UserEntity user = userRepository.findById(id).orElse(null);

    if (user != null) {
      userRepository.delete(user);
      return true;
    } else {
      return false;
    }
  }
}
