package com.apartment.vmoproject.api.controller;

import com.apartment.vmoproject.api.controller.dto.response.ApartmentDto;
import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.ResponseObject;
import com.apartment.vmoproject.api.repository.ApartmentRepository;
import com.apartment.vmoproject.api.service.ApartmentService;
import com.apartment.vmoproject.api.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/insert")
    public ResponseEntity<?> insertApartment(@RequestBody ApartmentDto apartmentDto){
        Apartment apartmentRequest = modelMapper.map(apartmentDto,Apartment.class);

        Apartment apartment = apartmentService.save(apartmentRequest);

        ApartmentDto apartmentResponse = modelMapper.map(apartment,ApartmentDto.class);

        ResponseObject response = ResponseObject.builder().
                message("Insert apartment successfully!!")
                .status("Created")
                .data(apartmentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllApartment(){
        List<Apartment> apartments = apartmentService.findAll();

        ResponseObject response = ResponseObject.builder().
                message("Find All!!")
                .status("OK")
                .data(apartments)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getApartmentById(@PathVariable("id") Long id){
        Optional<Apartment> apartment = apartmentService.findById(id);

        ResponseObject response = ResponseObject.builder().
                message("Find successfully!!")
                .status("OK")
                .data(apartment)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateApartment(@PathVariable("id") Long id, @RequestBody ApartmentDto apartmentDto){
        Apartment apartmentRequest = modelMapper.map(apartmentDto,Apartment.class);
        apartmentRequest.setId(id);


        Apartment apartmentResponse = apartmentService.save(apartmentRequest);

        ResponseObject response = ResponseObject.builder().
                message("Update apartment successfully!!")
                .status("OK")
                .data(apartmentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

}
