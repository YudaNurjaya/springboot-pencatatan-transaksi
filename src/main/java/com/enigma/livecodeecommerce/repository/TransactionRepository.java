package com.enigma.livecodeecommerce.repository;

import com.enigma.livecodeecommerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByDate(Date day);
    List<Transaction> findByDateBetween(Date start, Date end);
}
