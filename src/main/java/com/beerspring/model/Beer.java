package com.beerspring.model;

import javax.persistence.*;

/**
 * Created by Sims on 2/18/15.
 */

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String type;
    private Double abv;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Brewery brewery;

    public Beer(){};

    public Beer( String name, String type, Double abv, Brewery brewery )
    {
        this.name = name;
        this.type = type;
        this.abv = abv;
        this.brewery = brewery;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    @Override
    public String toString()
    {
        return String.format("Beer[name='%s', type='%s', brewery='%s']",
                                    name, type, brewery.getName());
    }

}
