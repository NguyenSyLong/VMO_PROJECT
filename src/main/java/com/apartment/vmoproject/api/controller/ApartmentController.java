package com.apartment.vmoproject.api.controller;

import com.apartment.vmoproject.api.controller.dto.response.ApartmentDto;
import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.ResponseObject;
import com.apartment.vmoproject.api.service.ApartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "api/v1/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/insert")
    public ResponseEntity<?> insertApartment(@RequestBody ApartmentDto apartmentDto) {
        Apartment apartmentRequest = modelMapper.map(apartmentDto, Apartment.class);

        Apartment apartment = apartmentService.save(apartmentRequest);

        ApartmentDto apartmentResponse = modelMapper.map(apartment, ApartmentDto.class);

        ResponseObject response = ResponseObject.builder().
                message("Insert apartment successfully!!")
                .status("Created")
                .data(apartmentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllApartment() {
        List<Apartment> apartments = apartmentService.findAll();

        ResponseObject response = ResponseObject.builder().
                message("Find All!!")
                .status("OK")
                .data(apartments)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/{pageNumber}/{pageSize}/{number}/{status}")
    public ResponseEntity<?> getAllApartmentByPaging(@PathVariable("pageNumber") int pageNumber,
                                                     @PathVariable("pageSize") int pageSize,
                                                     @PathVariable(name = "number") String number, @PathVariable(name = "status") String status) {

        Page<Apartment> apartments = null;

        if (!number.equals("null") && !status.equals("null")) {

            Boolean status1 = Boolean.parseBoolean(status);
            apartments = apartmentService.findByNumberContainingAndAndStatusWithPagination(pageNumber, pageSize, number, status1);
        } else if (!number.equals("null") && status.equals("null")) {
            Integer number1 = Integer.parseInt(number);
            apartments = apartmentService.findByNumberContainingWithPagination(pageNumber, pageSize, number1);
        } else if (number.equals("null") && !status.equals("null")) {
            Boolean status1 = Boolean.parseBoolean(status);
            apartments = apartmentService.findByNumberContainingAndAndStatusWithPagination(pageNumber, pageSize, "", status1);
        } else {
            apartments = apartmentService.findProductWithPagination(pageNumber, pageSize);
        }


        ResponseObject response = ResponseObject.builder().
                message("Find All!!")
                .status("OK")
                .data(apartments)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getApartmentById(@PathVariable("id") Long id) {
        Apartment apartment = apartmentService.findById(id);

        ResponseObject response = ResponseObject.builder().
                message("Find successfully!!")
                .status(HttpStatus.OK.toString())
                .data(apartment)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApartment(@PathVariable("id") Long id, @RequestBody ApartmentDto apartmentDto) {
        Apartment apartmentRequest = modelMapper.map(apartmentDto, Apartment.class);

        Apartment apartmentResponse = apartmentService.updateApartment(apartmentRequest, id);

        ResponseObject response = ResponseObject.builder().
                message("Update apartment successfully!!")
                .status("OK")
                .data(apartmentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

}
