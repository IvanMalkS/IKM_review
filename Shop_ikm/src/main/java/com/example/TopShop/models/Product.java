package com.example.TopShop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "city")
    private String city;

    @Column(name = "author")
    private String author;

    // Many-to-one relationship with Category
    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key to Category
    private Category category;

    // Many-to-one relationship with Supplier
    @ManyToOne
    @JoinColumn(name = "supplier_id") // Foreign key to Supplier
    private Supplier supplier;
}
