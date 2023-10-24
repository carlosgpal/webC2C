package com.c2c.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<User, String> {
    // select fields from user where userid = [param]
    User findByIduser(String iduser);

    @Query("SELECT p FROM Product p WHERE p.user_iduser = ?1")
    List<Product> findByUser_iduser(User user_iduser);
}
