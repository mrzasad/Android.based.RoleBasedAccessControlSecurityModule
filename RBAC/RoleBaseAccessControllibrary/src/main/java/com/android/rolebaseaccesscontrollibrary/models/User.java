package com.android.rolebaseaccesscontrollibrary.models;

public class User {

    private int id;
    private String name;
    private Role accessRole;

    public User(int id, String name, Role accessRole) {
        this.id = id;
        this.name = name;
        this.accessRole = accessRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(Role accessRole) {
        this.accessRole = accessRole;
    }
}
