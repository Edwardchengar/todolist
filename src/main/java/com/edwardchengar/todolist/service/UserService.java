package com.edwardchengar.todolist.service;

import com.edwardchengar.todolist.dao.model.SignInResponse;
import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import com.edwardchengar.todolist.dao.respository.UserRespository;
import com.edwardchengar.todolist.exceptions.UserException;
import com.edwardchengar.todolist.util.JwtUtil;
import com.edwardchengar.todolist.util.TodoConstant;
import com.google.common.base.Strings;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@GraphQLApi
public class UserService implements UserDetailsService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MongoUser user = userRespository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
        return new User(user.getUserName(), user.getPassword(), authorities);
    }

    @GraphQLMutation(name = TodoConstant.CREATE_USER)
    public boolean signUp(@GraphQLArgument(name = "user", defaultValue = "{}") MongoUser user) throws UserException{
        boolean signUpResult = false;
        try{
            if(Strings.isNullOrEmpty(user.getUserName()) || Strings.isNullOrEmpty(user.getPassword())){
                throw new Exception("NOT_ENOUGH_PARM");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            this.createUser(user);
            signUpResult = true;
        }catch (Exception e){
            throw new UserException("FAIL_TO_CREATE_USER");
        }
       return signUpResult;
    }


    public MongoUser createUser(MongoUser user){
        userRespository.insert(user);
        return user;
    }

    @GraphQLQuery(name = TodoConstant.SIGN_IN)
    public SignInResponse authenticate(String userName,String password) throws Exception{

        if(Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password)){
            throw new UserException("NOT_ENOUGH_INFOTMATION");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails = loadUserByUsername(userName);

        String token = jwtUtil.generateToken(userDetails);

        return new SignInResponse(token);
    }
}
