package com.enigma.livecodeecommerce.controller;
import com.enigma.livecodeecommerce.model.TransactionDetail;
import com.enigma.livecodeecommerce.model.request.TransactionDetailRequest;
import com.enigma.livecodeecommerce.model.response.SuccessResponse;
import com.enigma.livecodeecommerce.service.TransactionDetailService;
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
@RequestMapping("/detail")
public class TransactionDetailController {
    TransactionDetailService detailService;
    ModelMapper modelMapper;
    @Autowired
    public TransactionDetailController(TransactionDetailService detailService, ModelMapper modelMapper) {
        this.detailService = detailService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody TransactionDetailRequest detailRequest){
        TransactionDetail transactionDetail = modelMapper.map(detailRequest, TransactionDetail.class);
        TransactionDetail save = detailService.save(transactionDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<TransactionDetail>("Created",save));
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody TransactionDetailRequest detailRequest,@PathVariable Integer id){
        TransactionDetail transactionDetail = modelMapper.map(detailRequest, TransactionDetail.class);
        TransactionDetail save = detailService.update(transactionDetail,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<TransactionDetail>("Updated",save));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        detailService.delete(id);
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
        Iterable<TransactionDetail> get = detailService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<TransactionDetail>>("Success",get));
    }
}
