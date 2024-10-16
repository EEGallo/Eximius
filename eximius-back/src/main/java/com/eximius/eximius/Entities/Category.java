package com.eximius.eximius.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 500)
    private String description;

    public Category() {
    }
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}