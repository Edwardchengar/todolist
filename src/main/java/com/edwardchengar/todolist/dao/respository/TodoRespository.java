package com.edwardchengar.todolist.dao.respository;

import com.edwardchengar.todolist.dao.model.persistence.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRespository extends MongoRepository <Todo,String>{
    List<Todo>findByUsername(String userName);
}
