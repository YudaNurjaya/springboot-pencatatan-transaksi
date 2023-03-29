package com.enigma.livecodeecommerce.repository;

import com.enigma.livecodeecommerce.model.Transaction;
import com.enigma.livecodeecommerce.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    List<TransactionDetail> findByTransactionIn(Collection<Transaction> transaction);
}
