package com.enigma.livecodeecommerce.service;

import com.enigma.livecodeecommerce.exception.DataEmptyException;
import com.enigma.livecodeecommerce.exception.NotFoundException;
import com.enigma.livecodeecommerce.model.Product;
import com.enigma.livecodeecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IService<Product> {
    ProductRepository productRepository;
    FileService fileService;
    @Autowired
    public ProductService(ProductRepository productRepository, FileService fileService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    @Override
    public Product save(Product save) {
        return productRepository.save(save);
    }

    @Override
    public Product update(Product update, Integer id) {
        try {
            Optional<Product> find = productRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException();
            }
            find.get().setName(update.getName());
            find.get().setStock(update.getStock());
            find.get().setPrice(update.getPrice());
            find.get().setCategory(update.getCategory());
            return productRepository.save(find.get());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            productRepository.deleteById(id);
        }catch (NotFoundException e){
            throw new NotFoundException();
        }
    }

    @Override
    public Iterable<Product> findAll(Pageable pageable) {
        try {
            return productRepository.findAll(pageable);
        }catch (DataEmptyException e){
            throw new DataEmptyException();
        }
    }
}
