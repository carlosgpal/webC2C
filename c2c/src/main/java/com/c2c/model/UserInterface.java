package com.c2c.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<User, String>{
    //select fields from user where userid = [param]
    User findByIduser(String iduser);

}
