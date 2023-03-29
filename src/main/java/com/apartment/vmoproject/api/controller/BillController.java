package com.apartment.vmoproject.api.controller;

import com.apartment.vmoproject.api.controller.dto.response.BillDto;
import com.apartment.vmoproject.api.model.*;
import com.apartment.vmoproject.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private Bill_DetailService bill_detailService;

    @Autowired
    private Service_FeeService service_feeService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private DwellerService dwellerService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertBill(@RequestBody BillDto billDto) {
        Optional<Apartment> apartment = apartmentService.findById(billDto.getApartmentId());
        List<Service_Fee> listService = service_feeService.findByIdIsIn(billDto.getServiceId());

        long TotalPrice = 0;
        for (Service_Fee sv : listService) {
            TotalPrice += sv.getUnitPrice();

        }

        Date fromDate = null, toDate = null, dateOfPayment = null;
        try {
            fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(billDto.getFromDate());
            toDate = new SimpleDateFormat("dd/MM/yyyy").parse(billDto.getToDate());
            dateOfPayment = new SimpleDateFormat("dd/MM/yyyy").parse(billDto.getDateOfPayment());

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Bill billRequest = Bill.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .dateOfPayment(dateOfPayment)
                .totalPrice(TotalPrice)
                .apartment(apartment.get()).build();

        Bill billCreate = billService.save(billRequest);
        List<Bill_Detail> bill_details = new ArrayList<>();
        for (Service_Fee sv : listService) {
            Bill_Detail bill_detail = Bill_Detail.builder().bill(billCreate).service_fee(sv).build();
            bill_details.add(bill_detail);


        }
        billCreate.setBill_details(bill_details);

        Bill billResponse = billService.save(billCreate);

        for (Dweller dweller: apartment.get().getDwellers()) {
            if(dweller.getStatus()==true){
                emailSenderService.sendEmail(dweller.getEmail(),listService,billResponse);
            }

        }


        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find dweller successfully!")
                .data(billResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }

}
