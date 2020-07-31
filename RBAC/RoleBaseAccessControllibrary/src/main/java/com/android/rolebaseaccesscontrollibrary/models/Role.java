package com.android.rolebaseaccesscontrollibrary.models;

public class Role {
    private int id; //ids -> [1 = Normal, 2 = SuperAdmin, 3 = Admin]
    private String role; //[SuperAdmin, Admin, Normal]

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
