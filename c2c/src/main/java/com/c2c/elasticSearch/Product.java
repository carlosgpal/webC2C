package com.c2c.elasticSearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Objects;

@Document(indexName = "products")
public class Product {
    @Id
    private String idproduct;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Date, name = "date")
    private Date date;

    @Field(type = FieldType.Text, name = "place")
    private String place;

    @Field(type = FieldType.Text, name = "tag1")
    private String tag1;

    @Field(type = FieldType.Text, name = "tag2")
    private String tag2;

    @Field(type = FieldType.Text, name = "tag3")
    private String tag3;

    @Field(type = FieldType.Text, name = "tag4")
    private String tag4;

    @Field(type = FieldType.Text, name = "tag5")
    private String tag5;

    @Field(type = FieldType.Text, name = "user")
    private String user;


    public Product() {
    }

    public Product(String idproduct, String name, String description, double price, Date date, String place, String tag1, String tag2, String tag3, String tag4, String tag5, String user) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.place = place;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.tag5 = tag5;
        this.user = user;
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

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Product idproduct(String idproduct) {
        setIdproduct(idproduct);
        return this;
    }

    public Product name(String name) {
        setName(name);
        return this;
    }

    public Product description(String description) {
        setDescription(description);
        return this;
    }

    public Product price(double price) {
        setPrice(price);
        return this;
    }

    public Product date(Date date) {
        setDate(date);
        return this;
    }

    public Product place(String place) {
        setPlace(place);
        return this;
    }

    public Product tag1(String tag1) {
        setTag1(tag1);
        return this;
    }

    public Product tag2(String tag2) {
        setTag2(tag2);
        return this;
    }

    public Product tag3(String tag3) {
        setTag3(tag3);
        return this;
    }

    public Product tag4(String tag4) {
        setTag4(tag4);
        return this;
    }

    public Product tag5(String tag5) {
        setTag5(tag5);
        return this;
    }

    public Product user(String user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(idproduct, product.idproduct) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && price == product.price && Objects.equals(date, product.date) && Objects.equals(place, product.place) && Objects.equals(tag1, product.tag1) && Objects.equals(tag2, product.tag2) && Objects.equals(tag3, product.tag3) && Objects.equals(tag4, product.tag4) && Objects.equals(tag5, product.tag5) && user == product.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, name, description, price, date, place, tag1, tag2, tag3, tag4, tag5, user);
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
            ", tag1='" + getTag1() + "'" +
            ", tag2='" + getTag2() + "'" +
            ", tag3='" + getTag3() + "'" +
            ", tag4='" + getTag4() + "'" +
            ", tag5='" + getTag5() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
    
}