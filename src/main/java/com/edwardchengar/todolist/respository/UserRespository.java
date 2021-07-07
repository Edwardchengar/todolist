package com.edwardchengar.todolist.respository;

import com.edwardchengar.todolist.model.persistence.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends MongoRepository<MongoUser,String>{
    MongoUser findByUserName(String userName);
}
