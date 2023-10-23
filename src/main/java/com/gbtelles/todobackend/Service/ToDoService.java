package com.gbtelles.todobackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbtelles.todobackend.Entity.ToDo;
import com.gbtelles.todobackend.Entity.UserEntity;
import com.gbtelles.todobackend.Repository.ToDoRepository;
import com.gbtelles.todobackend.Repository.UserRepository;

@Service
public class ToDoService {
  @Autowired
  ToDoRepository toDoRepository;

  @Autowired
  UserRepository userRepository;

  public List<ToDo> getAll() {
    return toDoRepository.findAll();
  }

  public ToDo getById(long id) {
    return toDoRepository.findById(id).orElse(null);
  }

  public ToDo saveToDo(String toDoDescription, long userId) {
    ToDo toDo = new ToDo();
    
    UserEntity user = userRepository.findById(userId).orElse(null);
    toDo.setUserEntity(user);
    
    toDo.setToDoDescription(toDoDescription);
    toDo.setDone(false);

    return toDoRepository.save(toDo);
  }

  public ToDo updateToDo(long id, ToDo toDo) {
    ToDo updatedToDo = toDoRepository.findById(id).orElse(null);

    if (updatedToDo != null) {
      updatedToDo.setDone(toDo.getIsDone());
      updatedToDo.setToDoDescription(toDo.getToDoDescription());
      // updatedToDo.setUserEntity(toDo.getUserEntity());

      return toDoRepository.save(updatedToDo);
    } else {
      return null;
    }
  }

  public boolean deleteToDo(long id) {
    ToDo toDoToDelete = toDoRepository.findById(id).orElse(null);

    if (toDoToDelete != null) {
      toDoRepository.delete(toDoToDelete);

      return true;
    } else {
      return false;
    }
  }
}
