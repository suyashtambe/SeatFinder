package com.seatfinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Specifies the name of the database table
public class User {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies auto-increment for primary key
    private Long id; // Represents the primary key column in the database

    @Column(nullable = false) // Specifies mapping to a non-null database column
    private String fileName; // Represents a column storing the file name

    @Column(nullable = false) // Specifies mapping to a non-null database column
    private String rooms; // Represents a column storing information about rooms

    // Parameterized constructor to initialize the attributes
    public User(Long id, String fileName, String rooms) {
        this.id = id;
        this.fileName = fileName;
        this.rooms = rooms;
    }

    // Default constructor required by JPA
    public User() {
        // Default constructor
    }

    // Getter method for id attribute
    public Long getId() {
        return id;
    }

    // Setter method for id attribute
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method for fileName attribute
    public String getFileName() {
        return fileName;
    }

    // Setter method for fileName attribute
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Getter method for rooms attribute
    public String getRooms() {
        return rooms;
    }

    // Setter method for rooms attribute
    public void setRooms(String rooms) {
        this.rooms = rooms;
    }
}
