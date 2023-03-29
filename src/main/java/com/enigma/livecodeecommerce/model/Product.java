package com.enigma.livecodeecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Stock stock;
    @OneToOne(cascade = CascadeType.ALL)
    private Price price;
    @ManyToOne
    private Category category;
}
