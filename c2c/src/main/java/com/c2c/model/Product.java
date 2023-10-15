package com.c2c.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    private String idproduct;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Date date;

    @Column(length = 45, nullable = true)
    private String place;

    @Column(length = 45, nullable = true)
    private String image1;

    @Column(length = 45, nullable = true)
    private String image2;
    
    @Column(length = 45, nullable = true)
    private String image3;

    @Column(length = 45, nullable = true)
    private String image4;

    @Column(length = 45, nullable = true)
    private String image5;
    
    @Column(length = 45, nullable = true)
    private String tag1;

    @Column(length = 45, nullable = true)
    private String tag2;
    
    @Column(length = 45, nullable = true)
    private String tag3;

    @Column(length = 45, nullable = true)
    private String tag4;

    @Column(length = 45, nullable = true)
    private String tag5;

    @ManyToOne
    @JoinColumn(name = "user_iduser", nullable = false)
    private User user_iduser;

    public Product() {
    }

    public Product(String idproduct, String name, String description, double price, Date date, String place, String image1, String image2, String image3, String image4, String image5, String tag1, String tag2, String tag3, String tag4, String tag5, User user_iduser) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.place = place;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.tag5 = tag5;
        this.user_iduser = user_iduser;
    }

    public String getIdproduct() {
        return this.idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage1() {
        return this.image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return this.image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return this.image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return this.image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return this.image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getTag1() {
        return this.tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return this.tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return this.tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return this.tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getTag5() {
        return this.tag5;
    }

    public void setTag5(String tag5) {
        this.tag5 = tag5;
    }

    public User getUser_iduser() {
        return this.user_iduser;
    }

    public void setUser_iduser(User user_iduser) {
        this.user_iduser = user_iduser;
    }

    @Override
    public String toString() {
        return "{" +
            " idproduct='" + getIdproduct() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", price='" + getPrice() + "'" +
            ", date='" + getDate() + "'" +
            ", place='" + getPlace() + "'" +
            ", image1='" + getImage1() + "'" +
            ", image2='" + getImage2() + "'" +
            ", image3='" + getImage3() + "'" +
            ", image4='" + getImage4() + "'" +
            ", image5='" + getImage5() + "'" +
            ", tag1='" + getTag1() + "'" +
            ", tag2='" + getTag2() + "'" +
            ", tag3='" + getTag3() + "'" +
            ", tag4='" + getTag4() + "'" +
            ", tag5='" + getTag5() + "'" +
            ", user_iduser='" + getUser_iduser() + "'" +
            "}";
    }

}
