package com.enigma.livecodeecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "stock")
    private Integer stock;
    @OneToOne(mappedBy = "stock")
    private Product product;
}
