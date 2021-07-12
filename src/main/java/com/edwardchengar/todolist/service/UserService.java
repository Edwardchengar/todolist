package com.edwardchengar.todolist.service;

import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import com.edwardchengar.todolist.dao.respository.UserRespository;
import com.edwardchengar.todolist.util.TodoConstant;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@GraphQLApi
public class UserService implements UserDetailsService {
    @Autowired
    private UserRespository userRespository;

    @GraphQLMutation(name = TodoConstant.CREATE_USER)
    public MongoUser createUser(@GraphQLArgument(name = "user", defaultValue = "{}") MongoUser user){
        userRespository.insert(user);
        return user;
    }

    @Override
    @GraphQLQuery(name = TodoConstant.GET_USER)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MongoUser user = userRespository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
