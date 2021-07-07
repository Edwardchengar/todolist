package com.edwardchengar.todolist.controller;

import com.edwardchengar.todolist.model.persistence.Todo;
import com.edwardchengar.todolist.respository.TodoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoRespository todoRespository;

    @GetMapping("/{username}")
    public List<Todo> getTodo(@PathVariable("username") String username){
        return todoRespository.findByUsername(username);
    }

    @PostMapping("/{username}")
    public Todo insertTodo(@RequestBody Todo todoRequest){
        todoRespository.insert(todoRequest);
        return todoRequest;
    }


}
