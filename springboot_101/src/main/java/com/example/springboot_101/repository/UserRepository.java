package com.example.springboot_101.repository;

import com.example.springboot_101.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 根据手机号查找用户
    User findByPhone(String phone);
}
