package com.enigma.livecodeecommerce.service;

import com.enigma.livecodeecommerce.exception.DataEmptyException;
import com.enigma.livecodeecommerce.exception.NotFoundException;
import com.enigma.livecodeecommerce.model.Transaction;
import com.enigma.livecodeecommerce.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class TransactionService implements IService<Transaction> {
    TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction save(Transaction save) {
        try{
            return transactionRepository.save(save);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Transaction update(Transaction update, Integer id) {
        try{
            Optional<Transaction> find = transactionRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException();
            }
            find.get().setDate(update.getDate());
            return transactionRepository.save(find.get());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            transactionRepository.deleteById(id);
        }catch (NotFoundException e){
            throw new NotFoundException();
        }
    }

    @Override
    public Iterable<Transaction> findAll(Pageable pageable) {
        try {
            return transactionRepository.findAll(pageable);
        }catch (DataEmptyException e){
            throw new DataEmptyException();
        }
    }
}
