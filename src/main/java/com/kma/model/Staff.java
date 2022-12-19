package com.kma.model;

import java.sql.Timestamp;
import java.util.Date;

public class Staff {
    private Integer id;
    private String name;
    private String phone;
    private Timestamp birthday;
    private String email;
    private String gender;
    private String department;

    public Staff(Integer id, String name, String phone, Timestamp birthday, String email, String gender, String department) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Staff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
