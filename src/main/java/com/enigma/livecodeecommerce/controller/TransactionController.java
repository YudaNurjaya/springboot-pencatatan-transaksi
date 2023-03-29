package com.enigma.livecodeecommerce.controller;
import com.enigma.livecodeecommerce.model.Transaction;
import com.enigma.livecodeecommerce.model.request.TransactionRequest;
import com.enigma.livecodeecommerce.model.response.SuccessResponse;
import com.enigma.livecodeecommerce.service.TransactionService;
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
@RequestMapping("/transaction")
public class TransactionController {
    TransactionService transactionService;
    ModelMapper modelMapper;
    @Autowired
    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity save(@RequestBody TransactionRequest request){
        Transaction transaction = modelMapper.map(request, Transaction.class);
        Transaction save = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Transaction>("Created",save));
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody TransactionRequest request, Integer id){
        Transaction transaction = modelMapper.map(request, Transaction.class);
        Transaction save = transactionService.update(transaction,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Transaction>("Created",save));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        transactionService.delete(id);
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
        Iterable<Transaction> get = transactionService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Transaction>>("Success",get));
    }
}
