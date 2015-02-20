package com.beerspring.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by Sims on 2/18/15.
 */

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String externalId;
    private String name;
    private String type;

    @Column( length = 100000 )
    private String description;
    private Double ibu;
    private Double abv;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Brewery brewery;

    public Beer(){};

    public Beer( String name, String type, String description, Double ibu, Double abv, Brewery brewery )
    {
        this.name = name;
        this.type = type;
        this.description = description;
        this.ibu = ibu;
        this.abv = abv;
        this.brewery = brewery;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getIbu() {
        return ibu;
    }

    public void setIbu(Double ibu) {
        this.ibu = ibu;
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
                                    name, type, brewery);
    }

    @Override
    public int hashCode()
    {
      return new HashCodeBuilder(17, 31).append(externalId)
              .append(name)
              .append(type)
              .append(description)
              .append(ibu)
              .append(abv)
              .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Beer))
            return false;
        if (obj == this)
            return true;

        Beer beer = (Beer) obj;
        return new EqualsBuilder().append(name, beer.name)
                .append(type, beer.type)
                .append(description, beer.description)
                .append(ibu, beer.ibu)
                .append(abv, beer.abv)
                .isEquals();
    }

}
