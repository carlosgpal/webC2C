package com.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2c.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByIduser(String iduser);
}
