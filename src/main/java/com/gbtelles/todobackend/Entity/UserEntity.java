package com.gbtelles.todobackend.Entity;

import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "userEntity")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "userId")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long userId;

    @Column(name = "userName")
    private String userName;
  
    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPassword")
    private String userPassword;

    @OneToMany(mappedBy = "userEntity")
    private Set<ToDo> toDos = new HashSet<>();

    // public UserEntity(String userName, String userEmail, String userPassword) {
    //     this.userName = userName;
    //     this.userEmail = userEmail;
    //     this.userPassword = userPassword;
    //     this.toDos = new ArrayList<>();
    // }

    public long getUserId() {
        return userId;
    }
  
    public void setUserId(long userId) {
      this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getUserEmail() {
      return userEmail;
    }

    public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
    }

    public String getUserPassword() {
      return userPassword;
    }

    public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
    }

    public Set<ToDo> getToDos() {
      return toDos;
    }

    public void setToDos(Set<ToDo> toDos) {
      this.toDos = toDos;
    }
}
