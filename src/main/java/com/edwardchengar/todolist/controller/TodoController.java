package com.edwardchengar.todolist.controller;

import com.edwardchengar.todolist.dao.model.persistence.Todo;
import com.edwardchengar.todolist.dao.respository.TodoRespository;
import com.edwardchengar.todolist.service.TodoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("/{username}")
    public List<Todo> getTodo(@PathVariable("username") String username){
        return todoService.getTodoByUserName(username);
    }

    @PostMapping("/{username}")
    public Todo insertTodo(@RequestBody Todo todoRequest){
        return todoService.saveTodo(todoRequest);
    }


}
