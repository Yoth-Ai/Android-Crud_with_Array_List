package com.mardikh.android_crude_with_array_list;

public class User {
    private String name;
    private String gender;
    private String role;

    public User(String name, String gender, String role) {
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getRole() { return role; }

    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setRole(String role) { this.role = role; }
}
