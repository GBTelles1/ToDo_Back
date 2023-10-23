package com.gbtelles.todobackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbtelles.todobackend.Entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
  
}
