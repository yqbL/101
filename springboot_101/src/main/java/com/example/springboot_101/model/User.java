package com.example.springboot_101.model;

import jakarta.persistence.*;

@Entity
//@Table(name = "users") // 避免使用 MySQL 的保留关键字
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String password;

    // 默认构造方法
    public User() {
    }

    // 构造方法
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    // Getter 和 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
