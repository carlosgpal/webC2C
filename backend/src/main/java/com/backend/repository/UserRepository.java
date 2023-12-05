package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.User;

// This is the repository for the User model of the MySQL database
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByIduser(String iduser);
}
