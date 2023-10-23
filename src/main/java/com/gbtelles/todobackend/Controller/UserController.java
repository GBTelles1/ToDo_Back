package com.gbtelles.todobackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbtelles.todobackend.DTO.UserEntityDTO;
import com.gbtelles.todobackend.Entity.UserEntity;
import com.gbtelles.todobackend.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<UserEntity>> getAllUsers() {
    
    List<UserEntity> allUsers = userService.getAll();
    if (!allUsers.isEmpty()) {
      return new ResponseEntity<>(allUsers, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }    
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) {
    UserEntity user = userService.getById(id);

    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntityDTO userEntity) {
    return new ResponseEntity<>(userService.saveUserEntity(userEntity), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @RequestBody UserEntity user) {
    UserEntity updatedUser = userService.updateUserEntity(id, user);

    if (updatedUser != null) {
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {
    if (userService.deleteUserEntity(id)) {
      return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }
  }
}
