package com.c2c.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    private String idtag;

    @Column(length = 45, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = { CascadeType.ALL })
    private List<Product> products = new ArrayList<Product>();

    public Tag() {
    }

    public Tag(String idtag, String name, List<Product> products) {
        this.idtag = idtag;
        this.name = name;
        this.products = products;
    }

    public Tag(String idtag, String name) {
        this.idtag = idtag;
        this.name = name;
    }

    public String getIdtag() {
        return this.idtag;
    }

    public void setIdtag(String idtag) {
        this.idtag = idtag;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
                " idtag='" + getIdtag() + "'" +
                ", name='" + getName() + "'" +
                ", products='" + getProducts() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(idtag, tag.idtag) && Objects.equals(name, tag.name)
                && Objects.equals(products, tag.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtag, name, products);
    }

}
