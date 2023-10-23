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

import com.gbtelles.todobackend.DTO.ToDoDTO;
import com.gbtelles.todobackend.Entity.ToDo;
import com.gbtelles.todobackend.Repository.UserRepository;
import com.gbtelles.todobackend.Service.ToDoService;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
  @Autowired
  ToDoService toDoService;

  @Autowired
  UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<ToDo>> getAllToDos() {
    
    List<ToDo> allToDos = toDoService.getAll();
    if (!allToDos.isEmpty()) {
      return new ResponseEntity<>(allToDos, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }    
  }

  @GetMapping("/{id}")
  public ResponseEntity<ToDo> getToDoById(@PathVariable("id") long id) {
    ToDo toDo = toDoService.getById(id);

    if (toDo != null) {
      return new ResponseEntity<>(toDo, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/{userId}")
  public ResponseEntity<Long> createToDo(
    @RequestBody ToDoDTO toDo,
    @PathVariable Long userId
  ) {
    toDoService.saveToDo(toDo.getToDoDescription(), userId);
    return new ResponseEntity<>(
      userId,
      HttpStatus.CREATED
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<ToDo> updateToDo(
    @PathVariable long id,
    @RequestBody ToDo toDo
  ) {
    ToDo updatedtoDo = toDoService.updateToDo(id, toDo);
    HttpStatus stat = updatedtoDo == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    
    return new ResponseEntity<>(updatedtoDo, stat);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteToDo(@PathVariable long id) {
    if (toDoService.deleteToDo(id)) {
      return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }
  }
}
