package com.edwardchengar.todolist.controller;

import com.edwardchengar.todolist.dao.model.SignInRequest;
import com.edwardchengar.todolist.dao.model.SignInResponse;
import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import com.edwardchengar.todolist.service.UserService;
import com.edwardchengar.todolist.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
