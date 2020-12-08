package com.spring.timecounter.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "worker_counter")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "foreman_id")
    private User foreman;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    private Worker worker_id;


    private Timestamp time;
    private  String shift;

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAuthorName(){
        if (foreman!=null)
        return foreman.getFirstname()+" "+foreman.getLastname() ;
        else return " null";

    }



    public Message() {
    }

    public Message(Worker worker_id, Timestamp tag, User user, String shift) {
        this.shift=shift;
        this.foreman=user;
        this.worker_id = worker_id;
        this.time = tag;
    }

    public User getForeman() {
        return foreman;
    }

    public void setForeman(User foreman) {
        this.foreman = foreman;
    }

    public Worker getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Worker worker_id) {
        this.worker_id = worker_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTag() {
        return time;
    }

    public void setTag(Timestamp tag) {

        this.time =tag;
    }
    public User getAuthor() {
        return foreman;
    }

    public void setAuthor(User author) {
        this.foreman = author;
    }
}
