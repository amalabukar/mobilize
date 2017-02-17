package com.theironyard.entities;

/**
 * Created by amalabukar on 2/3/17.
 */

import javax.persistence.*;


/**
 * Created by amalabukar on 12/21/16.
 */

@Entity
@Table(name = "Causes")
public class Cause {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Cause (String name, String category, String location, int rating) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.user = user;
        this.rating=rating;


    }

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    int rating;

    @ManyToOne
    User user;


    public Cause() {
    }
}
