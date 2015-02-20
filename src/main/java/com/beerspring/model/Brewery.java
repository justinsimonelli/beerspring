package com.beerspring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sims on 2/18/15.
 */

@Entity
public class Brewery {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String city;
    private String state;
    private String country;

    @OneToMany
    private List<Beer> beers;

    public Brewery(){};

    public Brewery( String name, String city, String state, String country, List<Beer> beers )
    {
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.beers = beers;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }


}
