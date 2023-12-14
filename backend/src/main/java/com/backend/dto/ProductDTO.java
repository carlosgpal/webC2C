package com.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

// This is the DTO for the Product model of the MySQL database
public class ProductDTO {

    private String idproduct;
    private String name;
    private String description;
    private double price;
    private double rating;
    private LocalDateTime date;
    private String place;
    private List<ImageDTO> images;
    private List<TagDTO> tags;

    public ProductDTO() {
    }

    public ProductDTO(String idproduct, String name, String description, double price, double rating,
            LocalDateTime date, String place,
            List<ImageDTO> images, List<TagDTO> tags) {
        this.idproduct = idproduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.date = date;
        this.place = place;
        this.images = images;
        this.tags = tags;
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

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }
}