package com.example.WebDT.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private long price;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="category_id")
    private Category category;
}

