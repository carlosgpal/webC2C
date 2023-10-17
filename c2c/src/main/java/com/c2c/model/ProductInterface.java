package com.c2c.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInterface extends JpaRepository<Product, String>{
    //select fields from user where productid = [param]
    Product findByIdproduct(String idproduct);

    @Query("SELECT p FROM Product p WHERE p.user_iduser = ?1")
    List<Product> findByUser_iduser(User user_iduser);
}
