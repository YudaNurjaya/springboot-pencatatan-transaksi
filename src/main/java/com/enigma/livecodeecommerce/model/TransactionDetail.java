package com.enigma.livecodeecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transaction_detail")
@Data
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "qty")
    private Integer qty;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Transaction transaction;
}
