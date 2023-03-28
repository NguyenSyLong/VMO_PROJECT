package com.apartment.vmoproject.api.controller;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.model.ResponseObject;
import com.apartment.vmoproject.api.service.ApartmentService;
import com.apartment.vmoproject.api.service.DwellerService;
import com.apartment.vmoproject.api.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/dweller")
public class DwellerController {
    @Autowired
    private DwellerService dwellerService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${project.image}")
    private String path;

    @Autowired
    private FileService fileService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertDweller(
            @RequestParam("frontSideImage") MultipartFile frontSideImage,
            @RequestParam("backSideImage") MultipartFile backSideImage,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("dateOfBirth") String date,
            @RequestParam("gender") Boolean gender,
            @RequestParam("status") Boolean status,
            @RequestParam("apartmentId") Long apartmentId
            ) throws ParseException {

        Optional<Apartment> apartment = apartmentService.findById(apartmentId);

        String frontSidePath = this.fileService.saveFile(frontSideImage);

        String backSidePath = this.fileService.saveFile(backSideImage);

        Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(date);

        Dweller dwellerRequest = Dweller.builder()
                .name(name)
                .email(email)
                .frontSideImage(frontSidePath)
                .backSideImage(backSidePath)
                .dateOfBirth(dob)
                .gender(gender)
                .status(status)
                .apartment(apartment.get())
                .build();


        Dweller dwellerResponese = dwellerService.save(dwellerRequest);

        ResponseObject responseObject = ResponseObject.builder()
                .status("Created")
                .message("Add dweller successfully!")
                .data(dwellerResponese)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);

    }


    @GetMapping("")
    public ResponseEntity<?> getAllDweller(){
        Iterable<Dweller> dwellers = dwellerService.findAll();
        List<Dweller> dwellersResponse = new ArrayList<>();
        dwellers.forEach(dwellersResponse::add);


        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find alll")
                .data(dwellersResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }

    @GetMapping("/{apartmentId}")
    public ResponseEntity<?> getDwellerByApartmentId(@PathVariable("apartmentId") Long id){
        Optional<Apartment> apartment = apartmentService.findById(id);
        List<Dweller> dwellers = dwellerService.findByApartment(apartment.get());



        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find dweller successfully!")
                .data(dwellers)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }

}
