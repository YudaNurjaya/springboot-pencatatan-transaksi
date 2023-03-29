package com.enigma.livecodeecommerce.controller;

import com.enigma.livecodeecommerce.model.Category;
import com.enigma.livecodeecommerce.model.request.CategoryRequest;
import com.enigma.livecodeecommerce.model.response.SuccessResponse;
import com.enigma.livecodeecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;
    ModelMapper modelMapper;
    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CategoryRequest request){
        Category category = modelMapper.map(request, Category.class);
        Category save = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Category>("Created",save));
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody CategoryRequest request, @PathVariable Integer id){
        Category category = modelMapper.map(request, Category.class);
        Category save = categoryService.update(category,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Category>("Updated",save));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Deleted", "Id " + id + " is deleted"));
    }
    @GetMapping("/{size}/{page}/{sort}")
    public ResponseEntity findAll(@RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "asc") String sort){
        Pageable pageable = PageRequest.of(page-1,size, Sort.by("id").ascending());
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1,size, Sort.by("id").descending());
        }
        Iterable<Category> get = categoryService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Category>>("Success",get));
    }
}
