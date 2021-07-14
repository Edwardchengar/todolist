package com.edwardchengar.todolist.service.interfaces;

import com.edwardchengar.todolist.dao.model.persistence.Todo;

import java.util.List;

public interface TodoService {
    public List<Todo> getTodoByUserName(String username);
    public Todo saveTodo(Todo todo);
}
