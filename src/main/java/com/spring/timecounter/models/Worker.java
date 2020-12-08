package com.spring.timecounter.models;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    private Long uid;

    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    public Worker() {
    }

    public Worker(Long uid, String firstname, String lastname) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }



    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
