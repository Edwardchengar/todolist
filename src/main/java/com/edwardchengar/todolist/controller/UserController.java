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
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody MongoUser authenticationRequest){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(authenticationRequest.getPassword().trim());
        MongoUser user = new MongoUser(authenticationRequest.getUserName(),encodedPassword);
        userService.createUser(user);
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<SignInResponse> Authenticate(@RequestBody SignInRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());

        String token = jwtUtil.generateToken(userDetails);

        SignInResponse response = new SignInResponse(token);
        return ResponseEntity.ok(response);
    }

}
