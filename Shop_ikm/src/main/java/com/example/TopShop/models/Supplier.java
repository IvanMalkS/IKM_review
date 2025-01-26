package com.example.TopShop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Data // Lombok generates getters, setters, and other methods
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // Ensure this field exists

    private String contact;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
