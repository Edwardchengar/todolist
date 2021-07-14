package com.edwardchengar.todolist.service.interfaces;

import com.edwardchengar.todolist.dao.model.SignInResponse;
import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import com.edwardchengar.todolist.exceptions.UserException;
public interface UserService {
    public boolean signUp(MongoUser user) throws UserException;
    public SignInResponse authenticate(String userName, String password) throws Exception;

}
