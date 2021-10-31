package com.c.realtimecrud.Pojo;

public class User2 {
    private String id;
    private String name;
    private String email;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User2() {


    }

    public User2(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

