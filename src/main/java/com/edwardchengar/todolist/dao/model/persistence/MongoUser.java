package com.edwardchengar.todolist.dao.model.persistence;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;


@Document(collection = "users")
public class MongoUser {
    @Id
    private ObjectId _id;
    private String userName;
    private String password;
    private String email;
    private String accessToken;
    private List<String> roles;

    private Timestamp lastLogin;

    public MongoUser(){
    }

    public MongoUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public MongoUser(ObjectId _id, String userName, String password) {
        this._id = _id;
        this.userName = userName;
        this.password = password;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}