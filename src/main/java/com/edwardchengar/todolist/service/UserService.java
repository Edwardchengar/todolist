package com.edwardchengar.todolist.service;

import com.edwardchengar.todolist.model.persistence.MongoUser;
import com.edwardchengar.todolist.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserRespository userRespository;

    public MongoUser createUser(MongoUser user){
        userRespository.insert(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MongoUser user = userRespository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
