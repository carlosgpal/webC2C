package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Image {
    @Id
    private String idimage;

    @Column(length = 45, nullable = false)
    private String link;

    @ManyToMany(mappedBy = "images", cascade = { CascadeType.ALL })
    @JsonManagedReference
    private List<Product> products = new ArrayList<Product>();

    public Image() {
    }

    public Image(String idimage, String link, List<Product> products) {
        this.idimage = idimage;
        this.link = link;
        this.products = products;
    }

    public Image(String idimage, String link) {
        this.idimage = idimage;
        this.link = link;
    }

    public String getIdimage() {
        return this.idimage;
    }

    public void setIdimage(String idimage) {
        this.idimage = idimage;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "{" +
                " idproduct='" + getIdimage() + "'" +
                ", link='" + getLink() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Image)) {
            return false;
        }
        Image image = (Image) o;
        return Objects.equals(idimage, image.idimage) && Objects.equals(link, image.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idimage, link);
    }

}
