package com.edwardchengar.todolist.model;

public class SignInResponse {
    private String jwtToken;

    public SignInResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
