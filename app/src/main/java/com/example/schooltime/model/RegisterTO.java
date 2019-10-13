package com.example.schooltime.model;

public class RegisterTO {
    private String email, password, name, school;
    private int grade;
    //+시간표
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public RegisterTO(String email, String password, String name, String school, int grade) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.school = school;
        this.grade = grade;
    }
}
