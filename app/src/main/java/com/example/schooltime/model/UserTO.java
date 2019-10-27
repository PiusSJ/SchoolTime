package com.example.schooltime.model;

public class UserTO {
    private String id, password, name;

    public String getId() {
        return id;
    }

    public void setId(String email) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserTO(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
