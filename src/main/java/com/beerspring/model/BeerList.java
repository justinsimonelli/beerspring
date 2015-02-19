package com.beerspring.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Sims on
 * 2/18/15.
 */

@Entity
public class BeerList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    private String name;
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Beer> beers;
    private Date dateIntended;
    private Date dateInserted;
    private Date dateUpdated;

    public BeerList(){}

    public BeerList( User user, String name, List<Beer> beers, Date dateIntended, Date dateInserted, Date dateUpdated )
    {
        this.user = user;
        this.name = name;
        this.beers = beers;
        this.dateIntended = dateIntended;
        this.dateInserted = dateInserted;
        this.dateUpdated = dateUpdated;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    public Date getDateIntended() {
        return dateIntended;
    }

    public void setDateIntended(Date dateIntended) {
        this.dateIntended = dateIntended;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }


    @Override
    public String toString()
    {
        return String.format("BeerList[user='%s', name='%name', beers='%d']", user.getUserName(), name, beers);
    }


}
