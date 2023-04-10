package com.apartment.vmoproject.api.controller;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.model.ResponseObject;
import com.apartment.vmoproject.api.service.ApartmentService;
import com.apartment.vmoproject.api.service.DwellerService;
import com.apartment.vmoproject.api.service.FileService;
import org.hibernate.annotations.NotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
            @RequestParam("apartmentId") Long apartmentId,
            @RequestParam("isRepresent") Boolean isRepresent
    ) throws ParseException {

        Apartment apartment = apartmentService.findById(apartmentId);

        String frontSidePath = this.fileService.saveFile(frontSideImage);

        String backSidePath = this.fileService.saveFile(backSideImage);

        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        apartment.setStatus(true);
        Dweller dwellerRequest = Dweller.builder()
                .name(name)
                .email(email)
                .frontSideImage(frontSidePath)
                .backSideImage(backSidePath)
                .dateOfBirth(dob)
                .gender(gender)
                .status(status)
                .apartment(apartment)
                .isRepresent(isRepresent)
                .build();


        Dweller dwellerResponese = dwellerService.save(dwellerRequest);


        ResponseObject responseObject = ResponseObject.builder()
                .status("Created")
                .message("Add dweller successfully!")
                .data(dwellerResponese)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editDweller(
            @PathVariable("id") Long id,
            @RequestParam(name = "frontSideImage",required = false) MultipartFile frontSideImage,
            @RequestParam(name = "backSideImage",required = false) MultipartFile backSideImage,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("dateOfBirth") String date,
            @RequestParam("gender") Boolean gender,
            @RequestParam("status") Boolean status,
            @RequestParam("apartmentId") Long apartmentId,
            @RequestParam("isRepresent") Boolean isRepresent
    ) throws ParseException {
        String frontSidePath, backSidePath;
        Apartment apartment = apartmentService.findById(apartmentId);
        Optional<Dweller> dweller = dwellerService.findById(id);
        if(frontSideImage != null){
            frontSidePath = this.fileService.saveFile(frontSideImage);
        }else{
            frontSidePath = dweller.get().getFrontSideImage();
        }
        if(backSideImage != null){
            backSidePath = this.fileService.saveFile(backSideImage);
        }else{
            backSidePath = dweller.get().getBackSideImage();
        }



        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        if(status==false){
            if(apartment.getDwellers().size()==1){
                apartment.setStatus(false);
            }
        }else{
            apartment.setStatus(true);
        }

        Dweller dwellerRequest = Dweller.builder()
                .name(name)
                .email(email)
                .frontSideImage(frontSidePath)
                .backSideImage(backSidePath)
                .dateOfBirth(dob)
                .gender(gender)
                .status(status)
                .apartment(apartment)
                .isRepresent(isRepresent)
                .id(dweller.get().getId())
                .build();


        Dweller dwellerResponese = dwellerService.save(dwellerRequest);

        ResponseObject responseObject = ResponseObject.builder()
                .status("Updated")
                .message("Add dweller successfully!")
                .data(dwellerResponese)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }


    @GetMapping("")
    public ResponseEntity<?> getAllDweller() {
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

    @GetMapping("/{pageNumber}/{pageSize}/{name}/{status}")
    public ResponseEntity<?> getDwellerByPaging(@PathVariable("pageNumber") int pageNumber,
                                                @PathVariable("pageSize") int pageSize,
                                                @PathVariable(name="name") String name,@PathVariable(name = "status") String status) {
        Page<Dweller> dwellers = null;

        if (!name.equals("null")&&!status.equals("null")){

            Boolean status1 = Boolean.parseBoolean(status);
            dwellers = dwellerService.findByNameContainingAndStatusWithPagination(pageNumber,pageSize,name,status1);
        }else if(!name.equals("null") && status.equals("null")){

            dwellers = dwellerService.findByNameContainingWithPagination(pageNumber,pageSize,name);
        }else if(name.equals("null") && !status.equals("null")){
            Boolean status1 = Boolean.parseBoolean(status);
            dwellers = dwellerService.findByNameContainingAndStatusWithPagination(pageNumber,pageSize,"",status1);
        }else {
            dwellers = dwellerService.findByNameContainingWithPagination(pageNumber,pageSize,"");
        }


        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find alll")
                .data(dwellers)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getDwellerById(@PathVariable("id") Long id) {

        Optional<Dweller> dweller = dwellerService.findById(id);


        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find dweller successfully!")
                .data(dweller.get())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }


}
