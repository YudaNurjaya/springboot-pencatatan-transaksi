package com.enigma.livecodeecommerce.service;

import com.enigma.livecodeecommerce.exception.DataEmptyException;
import com.enigma.livecodeecommerce.exception.NotFoundException;
import com.enigma.livecodeecommerce.model.Stock;
import com.enigma.livecodeecommerce.model.TransactionDetail;
import com.enigma.livecodeecommerce.repository.TransactionDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TransactionDetailService implements IService<TransactionDetail>{
    TransactionDetailRepository detailRepository;
    ProductService productService;
    @Autowired
    public TransactionDetailService(TransactionDetailRepository detailRepository,ProductService productService) {
        this.detailRepository = detailRepository;
        this.productService = productService;
    }

    @Override
    public TransactionDetail save(TransactionDetail save) {
        try {
            Optional<TransactionDetail> find = detailRepository.findById(save.getId());
            Stock stock = find.get().getProduct().getStock();
            stock.setStock(stock.getStock()-find.get().getQty());
            return detailRepository.save(save);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TransactionDetail update(TransactionDetail update, Integer id) {
        try {
            Optional<TransactionDetail> find = detailRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException();
            }
            find.get().setQty(update.getQty());
            find.get().setTransaction(update.getTransaction());
            find.get().setProduct(update.getProduct());
            return detailRepository.save(find.get());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            detailRepository.deleteById(id);
        }catch (NotFoundException e){
            throw new NotFoundException();
        }
    }

    @Override
    public Iterable<TransactionDetail> findAll(Pageable pageable) {
        try {
            return detailRepository.findAll(pageable);
        }catch (DataEmptyException e){
            throw new DataEmptyException();
        }
    }
}
