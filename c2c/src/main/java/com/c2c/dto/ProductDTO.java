package com.c2c.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDTO {

    private String idproduct;
    private String name;
    private String description;
    private double price;
    private LocalDateTime date;
    private String place;
    private List<String> userIds;
    private List<String> imageIds;
    private List<String> tagIds;

    public ProductDTO() {
    }

    public ProductDTO(String idproduct, String name, String description, double price, LocalDateTime date, String place,
            List<String> userIds, List<String> imageIds, List<String> tagIds) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.place = place;
        this.userIds = userIds;
        this.imageIds = imageIds;
        this.tagIds = tagIds;
    }

    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    // Puedes agregar métodos utilitarios si son necesarios
}