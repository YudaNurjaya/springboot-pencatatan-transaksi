package com.enigma.livecodeecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "price")
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price")
    private Double price;
    @OneToOne(mappedBy = "price")
    private Product product;
}
