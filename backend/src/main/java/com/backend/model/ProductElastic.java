package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Objects;

// This is the model class for the Product table in the elasticsearch database
@Document(indexName = "product")
public class ProductElastic {
    @Id
    private String idproduct;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Double, name = "rating")
    private double rating;

    @Field(type = FieldType.Date, name = "date", format = DateFormat.date_time)
    private Date date;

    @Field(type = FieldType.Text, name = "place")
    private String place;

    @Field(type = FieldType.Text, name = "thumbnailimg")
    private String thumbnailimg;

    // Represents a nested object
    @Field(type = FieldType.Nested, name = "tags")
    private List<TagElastic> tags;

    @Field(type = FieldType.Text, name = "user")
    private String user;

    public ProductElastic() {
    }

    public ProductElastic(String idproduct, String name, String description, double price, double rating, Date date,
            String place, String thumbnailimg, List<TagElastic> tags, String user) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.date = date;
        this.place = place;
        this.thumbnailimg = thumbnailimg;
        this.tags = tags;
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

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public String getThumbnailimg() {
        return this.thumbnailimg;
    }

    public void setThumbnailimg(String thumbnailimg) {
        this.thumbnailimg = thumbnailimg;
    }

    public List<TagElastic> getTags() {
        return this.tags;
    }

    public void setTags(List<TagElastic> tags) {
        this.tags = tags;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                " idproduct='" + getIdproduct() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", price='" + getPrice() + "'" +
                ", rating='" + getRating() + "'" +
                ", date='" + getDate() + "'" +
                ", place='" + getPlace() + "'" +
                ", thumbnailimg='" + getThumbnailimg() + "'" +
                ", tags='" + getTags() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductElastic)) {
            return false;
        }
        ProductElastic productElastic = (ProductElastic) o;
        return Objects.equals(idproduct, productElastic.idproduct) && Objects.equals(name, productElastic.name)
                && Objects.equals(description, productElastic.description) && price == productElastic.price
                && Objects.equals(date, productElastic.date) && Objects.equals(place, productElastic.place)
                && Objects.equals(tags, productElastic.tags) && Objects.equals(user, productElastic.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, name, description, price, rating, date, place, thumbnailimg, tags, user);
    }

}