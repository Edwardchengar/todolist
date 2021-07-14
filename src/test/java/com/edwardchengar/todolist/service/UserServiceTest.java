package com.edwardchengar.todolist.service;

import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import com.edwardchengar.todolist.dao.respository.UserRespository;
import com.edwardchengar.todolist.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

class UserServiceTest {

    @MockBean
    private UserRespository userRespository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private MongoUser mockUpUser;

    @Test
    void loadUserByUsername() {
//        Mockito.when(userRespository.findByUserName("test")).thenReturn(mockUpUser);
//        Mockito.when(mockUpUser.getUserName()).thenReturn("test");
//        assertThat(userServiceImpl.loadUserByUsername("test").getUsername()).isEqualTo("test");
    }

    @Test
    void signUp() {
    }

    @Test
    void authenticate() {
    }
}