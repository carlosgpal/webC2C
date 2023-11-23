package com.c2c.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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
    private LocalDateTime date;

    @Column(length = 45, nullable = true)
    private String place;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JsonBackReference
    @JoinTable(name = "product_has_image", joinColumns = {
            @JoinColumn(name = "product_idproduct") }, inverseJoinColumns = {
                    @JoinColumn(name = "image_idimage") })
    private List<Image> images = new ArrayList<Image>();

    public void addImage(Image image) {
        images.add(image);
        image.getProducts().add(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.getProducts().remove(this);
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JsonBackReference
    @JoinTable(name = "product_has_tag", joinColumns = {
            @JoinColumn(name = "product_idproduct") }, inverseJoinColumns = {
                    @JoinColumn(name = "tag_idtag") })
    private List<Tag> tags = new ArrayList<Tag>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getProducts().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getProducts().remove(this);
    }

    @ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "products")
    @JsonManagedReference
    private List<User> users = new ArrayList<User>();

    public Product() {
    }

    public Product(String idproduct, String name, String description, double price, LocalDateTime date, String place,
            List<Image> images, List<Tag> tags, List<User> users) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.place = place;
        this.images = images;
        this.tags = tags;
        this.users = users;
    }

    public Product(String idproduct, String name, String description, double price, LocalDateTime date, String place) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.place = place;
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

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(idproduct, product.idproduct) && Objects.equals(name, product.name)
                && Objects.equals(description, product.description) && price == product.price
                && Objects.equals(date, product.date) && Objects.equals(place, product.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, name, description, price, date, place);
    }

}