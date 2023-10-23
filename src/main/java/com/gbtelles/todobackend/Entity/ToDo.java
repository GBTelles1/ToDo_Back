package com.gbtelles.todobackend.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todo")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "toDoId")
public class ToDo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "toDoId")
  private long toDoId;

  @Column(name = "toDoDescription")
  private String toDoDescription;

  @Column(name = "isDone")
  private boolean isDone;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  public long getToDoId() {
    return toDoId;
  }

  public String getToDoDescription() {
    return toDoDescription;
  }

  public void setToDoDescription(String toDoDescription) {
    this.toDoDescription = toDoDescription;
  }

  public boolean getIsDone() {
    return isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity user) {
    this.userEntity = user;
  }
}
