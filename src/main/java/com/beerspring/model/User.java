package com.beerspring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by markryan on 2/16/15.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<CheckIn> checkIns = new ArrayList<>();

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<BeerList> beerLists = new ArrayList<>();

    protected User(){}

    public User(String userName, String email, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() { return email; }

    public void setEmail( String email ){ this.email = email; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public List<BeerList> getBeerLists() {
        return beerLists;
    }

    public void setBeerLists(List<BeerList> beerLists) {
        this.beerLists = beerLists;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s', email='%s' firstName='%s', lastName='%s']",
                id, userName, email, firstName, lastName);
    }

}
