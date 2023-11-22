package com.c2c.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String iduser;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(length = 45, nullable = false)
    private String pass;

    @Column(nullable = false)
    private LocalDateTime lasttime;

    @Column(nullable = false)
    private boolean isverify;

    @Column(length = 45)
    private String verifylink;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "user_has_product", joinColumns = { @JoinColumn(name = "user_iduser") }, inverseJoinColumns = {
            @JoinColumn(name = "product_idproduct") })
    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        products.add(product);
        product.getUsers().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getUsers().remove(this);
    }

    public User() {
    }

    public User(String iduser, String name, String email, String pass, LocalDateTime lasttime, boolean isverify,
            String verifylink,
            List<Product> products) {
        this.iduser = iduser;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.lasttime = lasttime;
        this.isverify = isverify;
        this.verifylink = verifylink;
        this.products = products;
    }

    public User(String iduser, String name, String email, String pass, LocalDateTime lasttime, boolean isverify,
            String verifylink) {
        this.iduser = iduser;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.lasttime = lasttime;
        this.isverify = isverify;
        this.verifylink = verifylink;
    }

    public String getIduser() {
        return this.iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LocalDateTime getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(LocalDateTime lasttime) {
        this.lasttime = lasttime;
    }

    public boolean isIsverify() {
        return this.isverify;
    }

    public boolean getIsverify() {
        return this.isverify;
    }

    public void setIsverify(boolean isverify) {
        this.isverify = isverify;
    }

    public String getVerifylink() {
        return this.verifylink;
    }

    public void setVerifylink(String verifylink) {
        this.verifylink = verifylink;
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
                " iduser='" + getIduser() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", pass='" + getPass() + "'" +
                ", lasttime='" + getLasttime() + "'" +
                ", isverify='" + isIsverify() + "'" +
                ", verifylink='" + getVerifylink() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(iduser, user.iduser) && Objects.equals(name, user.name)
                && Objects.equals(email, user.email) && Objects.equals(pass, user.pass)
                && Objects.equals(lasttime, user.lasttime) && isverify == user.isverify
                && Objects.equals(verifylink, user.verifylink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, name, email, pass, lasttime, isverify, verifylink);
    }

}