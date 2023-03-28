package com.apartment.vmoproject.api.controller;


import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.model.ResponseObject;
import com.apartment.vmoproject.api.model.Service_Fee;
import com.apartment.vmoproject.api.service.impl.Service_FeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/service_fee")
public class Service_FeeController {

    @Autowired
    private Service_FeeServiceImpl service_feeService;


    @PostMapping("/insert")
    public ResponseEntity<?> insertServiceFee(@RequestBody Service_Fee service_fee) {
        Service_Fee serviceFeeResponse = service_feeService.save(service_fee);

        ResponseObject response = ResponseObject.builder()
                .status("Created")
                .message("Insert successfully!!")
                .data(serviceFeeResponse)
                .build();
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("")
    public ResponseEntity<?> getAllService_Fee() {
        Iterable<Service_Fee> serviceFees = service_feeService.findAll();
        List<Service_Fee> serviceFeeResponse = new ArrayList<>();
        serviceFees.forEach(serviceFeeResponse::add);

        ResponseObject response = ResponseObject.builder()
                .status("OK")
                .message("Find All!!")
                .data(serviceFeeResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
//    @PutMapping("/{id}")



}
