package com.edwardchengar.todolist.controller;

import com.edwardchengar.todolist.dao.model.SignInRequest;
import com.edwardchengar.todolist.dao.model.SignInResponse;
import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import com.edwardchengar.todolist.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.edwardchengar.todolist.util.TodoConstant.USER_PATTERN;

@RestController
@RequestMapping(USER_PATTERN)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody MongoUser authenticationRequest) throws Exception{
        userService.signUp(authenticationRequest);
        return ResponseEntity.ok(authenticationRequest);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<SignInResponse> Authenticate(@RequestBody SignInRequest authenticationRequest) throws Exception {
        SignInResponse response = userService.authenticate(authenticationRequest.getUserName(),authenticationRequest.getPassword());
        return ResponseEntity.ok(response);
    }

}
