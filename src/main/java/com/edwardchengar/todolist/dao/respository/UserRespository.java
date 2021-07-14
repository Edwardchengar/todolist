package com.edwardchengar.todolist.dao.respository;

import com.edwardchengar.todolist.dao.model.persistence.MongoUser;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan()
public interface UserRespository extends MongoRepository<MongoUser,String>{
    MongoUser findByUserName(String userName);
}
