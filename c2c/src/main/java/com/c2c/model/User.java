package com.c2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String iduser;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @Column(length = 45, nullable = false)
    private String pass;

    @Column
    private Date lasttime;

    @Column
    private boolean isverify;

    @Column(length = 45)
    private String verifylink;

    public User() {
    }

    public User(String iduser, String name, String pass, Date lasttime, boolean isverify, String verifylink) {
        this.iduser = iduser;
        this.name = name;
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

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(Date lasttime) {
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(iduser, user.iduser) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, name);
    }

    @Override
    public String toString() {
        return "{" +
                " iduser='" + getIduser() + "'" +
                ", name='" + getName() + "'" +
                ", pass='" + getPass() + "'" +
                ", lasttime='" + getLasttime() + "'" +
                ", isverify='" + isIsverify() + "'" +
                ", verifylink='" + getVerifylink() + "'" +
                "}";
    }

}
