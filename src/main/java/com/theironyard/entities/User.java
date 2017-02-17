package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by amalabukar on 2/3/17.
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique =true)
    String name;
    @Column(nullable = false)
    public String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
