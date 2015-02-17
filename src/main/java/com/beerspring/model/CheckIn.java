package com.beerspring.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by markryan on 2/16/15.
 */
@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    private User user;

    private Date date;

    //Needed by JPA
    protected CheckIn(){}

    public CheckIn(User user, Date date) {
        this.user = user;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }
}
