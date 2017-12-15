package com.luoman.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mango on 2017/4/27.
 */
@Entity
@Table(name="UserEntity",schema = "springMVC")
public class UserEntity {
    private Integer id;
    private String userName;
    private String identity;
    private String institution;
    private String password;
    private String major;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userName", nullable = false)
    public String getUserName() {
        return userName;}

    public void setUserName(String uname) {
        this.userName = uname;
    }

    @Basic
    @Column(name = "identity" , nullable = false)
    public String getIdentity() {
        return identity;}

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Basic
    @Column(name = "institution" , nullable = false)
    public String getInstitution() {
        return institution;}

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Basic
    @Column(name = "password" , nullable = false)
    public String getPassword() {
        return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "major" , nullable = false)
    public String getMajor() {
        return major;}

    public void setMajor(String major) {
        this.major = major;
    }


    @Basic
    @Column(name = "pubDate",nullable = false)
    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }




}
