package com.edwardchengar.todolist.respository;

import com.edwardchengar.todolist.model.persistence.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRespository extends MongoRepository <Todo,String>{
    public List<Todo>findByUsername(String userName);
}
