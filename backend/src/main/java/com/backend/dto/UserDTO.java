package com.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

// This is the DTO for the User model of the MySQL database
public class UserDTO {

    private String iduser;
    private String name;
    private String profilepic;
    private String email;
    private String pass;
    private LocalDateTime lasttime;
    private boolean isverify;
    private String verifylink;
    private List<ProductDTO> products;

    public UserDTO() {
    }

    public UserDTO(String iduser, String name, String profilepic, String email, String pass, LocalDateTime lasttime,
            boolean isverify,
            String verifylink, List<ProductDTO> products) {
        this.iduser = iduser;
        this.name = name;
        this.profilepic = profilepic;
        this.email = email;
        this.pass = pass;
        this.lasttime = lasttime;
        this.isverify = isverify;
        this.verifylink = verifylink;
        this.products = products;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepic() {
        return this.profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getEmail() {
        return email;
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
        return lasttime;
    }

    public void setLasttime(LocalDateTime lasttime) {
        this.lasttime = lasttime;
    }

    public boolean getIsverify() {
        return isverify;
    }

    public void setIsverify(boolean isverify) {
        this.isverify = isverify;
    }

    public String getVerifylink() {
        return verifylink;
    }

    public void setVerifylink(String verifylink) {
        this.verifylink = verifylink;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> productIds) {
        this.products = productIds;
    }

}
