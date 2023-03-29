package com.enigma.livecodeecommerce.service;

import com.enigma.livecodeecommerce.exception.DataEmptyException;
import com.enigma.livecodeecommerce.exception.NotFoundException;
import com.enigma.livecodeecommerce.model.Category;
import com.enigma.livecodeecommerce.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CategoryService implements IService<Category>{
    CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category save) {
        try {
            return categoryRepository.save(save);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Category update(Category update, Integer id) {
        try {
            Optional<Category> find = categoryRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException();
            }
            find.get().setName(update.getName());
            return categoryRepository.save(find.get());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
        }catch (NotFoundException e){
            throw new NotFoundException();
        }
    }

    @Override
    public Iterable<Category> findAll(Pageable pageable) {
        try {
            return categoryRepository.findAll(pageable);
        }catch (DataEmptyException e){
            throw new DataEmptyException();
        }
    }
}
