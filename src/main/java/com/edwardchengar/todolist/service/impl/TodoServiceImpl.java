package com.edwardchengar.todolist.service.impl;

import com.edwardchengar.todolist.dao.model.persistence.Todo;
import com.edwardchengar.todolist.dao.respository.TodoRespository;
import com.edwardchengar.todolist.service.interfaces.TodoService;
import com.edwardchengar.todolist.util.TodoConstant;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GraphQLApi
public class TodoServiceImpl implements TodoService {
    TodoRespository todoRespository;

    @Autowired
    public TodoServiceImpl(TodoRespository todoRespository) {
        this.todoRespository = todoRespository;
    }

    @GraphQLQuery(name = TodoConstant.GET_TODO_BY_USERNAME)
    public List<Todo> getTodoByUserName(String username){
        return todoRespository.findByUsername(username);
    }

    @GraphQLMutation(name = TodoConstant.SAVE_TODO)
    public Todo saveTodo(@GraphQLArgument(name = "todo", defaultValue = "{}")Todo todo){
        todoRespository.insert(todo);
        return todo;
    }
}
