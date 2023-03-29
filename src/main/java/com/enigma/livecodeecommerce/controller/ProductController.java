package com.enigma.livecodeecommerce.controller;
import com.enigma.livecodeecommerce.model.Product;
import com.enigma.livecodeecommerce.model.request.ProductRequest;
import com.enigma.livecodeecommerce.model.response.SuccessResponse;
import com.enigma.livecodeecommerce.service.FileService;
import com.enigma.livecodeecommerce.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    ModelMapper modelMapper;
    FileService fileService;
    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, FileService fileService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }
    @PostMapping
    public ResponseEntity save(@RequestBody ProductRequest request){
        Product product = modelMapper.map(request, Product.class);
        Product save = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Product>("Created",save));
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody ProductRequest request,@PathVariable Integer id){
        Product product = modelMapper.map(request,Product.class);
        Product update = productService.update(product,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Product>("Updated",update));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Deleted","Id " + id + " is deleted"));
    }
    @GetMapping("/{size}/{page}/{sort}")
    public ResponseEntity findAll(@RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "asc") String sort){
        Pageable pageable = PageRequest.of(page-1,size, Sort.by("id").ascending());
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1,size, Sort.by("id").descending());
        }
        Iterable<Product> get = productService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Product>>("Success",get));
    }
}
