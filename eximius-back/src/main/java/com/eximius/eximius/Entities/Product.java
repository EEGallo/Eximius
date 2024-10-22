package com.eximius.eximius.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_product")
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String size;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}