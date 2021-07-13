package com.edwardchengar.todolist.exceptions;

import org.slf4j.Logger;

public class UserException extends Exception{
    public UserException(String message){
        //TODO: add logging
        super(message);
    }
}
