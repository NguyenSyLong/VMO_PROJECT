package com.apartment.vmoproject.api.controller;

import com.apartment.vmoproject.api.controller.dto.response.BillDto;
import com.apartment.vmoproject.api.model.*;
import com.apartment.vmoproject.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Apartment apartment = apartmentService.findById(billDto.getApartmentId());
        List<Service_Fee> listService = service_feeService.findByIdIsIn(billDto.getServiceId());

        long TotalPrice = 0;
        for (Service_Fee sv : listService) {
            if(sv.getId()==1){
                TotalPrice += sv.getUnitPrice()*billDto.getWaterConsumption();
            }else if (sv.getId()==2) {
                TotalPrice += sv.getUnitPrice()*billDto.getElectricConsumption();
            }else{
                TotalPrice += sv.getUnitPrice();
            }


        }

        Date fromDate = null, toDate = null, dateOfPayment = null;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(billDto.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(billDto.getToDate());
//            dateOfPayment = new SimpleDateFormat("dd/MM/yyyy").parse(billDto.getDateOfPayment());

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Bill billRequest = Bill.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .totalPrice(TotalPrice)
                .waterConsumption(billDto.getWaterConsumption())
                .electricConsumption(billDto.getElectricConsumption())
                .apartment(apartment).build();

        Bill billCreate = billService.save(billRequest);
        List<Bill_Detail> bill_details = new ArrayList<>();
        for (Service_Fee sv : listService) {
            Bill_Detail bill_detail = Bill_Detail.builder().bill(billCreate).service_fee(sv).build();
            bill_details.add(bill_detail);


        }
        billCreate.setBill_details(bill_details);

        Bill billResponse = billService.save(billCreate);

//        for (Dweller dweller: apartment.get().getDwellers()) {
//            if(dweller.getStatus()==true){
//                emailSenderService.sendEmail(dweller.getEmail(),listService,billResponse);
//            }
//
//        }
//
//
        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find dweller successfully!")
                .data(billResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBill(@PathVariable("id") Long id, @RequestBody BillDto billDto) {
        Apartment apartment = apartmentService.findById(billDto.getApartmentId());
        List<Service_Fee> listService = service_feeService.findByIdIsIn(billDto.getServiceId());
        Bill bill = billService.findById(id).get();





        List<Bill_Detail> bill_detailo = bill_detailService.findByBill(bill);

        for (Bill_Detail bd: bill_detailo) {
            Optional<Service_Fee> sf = service_feeService.findById(bd.getService_fee().getId());
            sf.get().getBill_details().remove(bd);
            service_feeService.save(sf.get());
            bill.removeBD(bd);


        }


        for (Service_Fee sv : listService) {
            Bill_Detail bill_detail = Bill_Detail.builder().bill(bill).service_fee(sv).build();
            bill.addBillDetail(bill_detail);

        }

        long TotalPrice = 0;
        for (Service_Fee sv : listService) {
            if(sv.getId()==1){
                TotalPrice += sv.getUnitPrice()*billDto.getWaterConsumption();
            }else if (sv.getId()==2) {
                TotalPrice += sv.getUnitPrice()*billDto.getElectricConsumption();
            }else{
                TotalPrice += sv.getUnitPrice();
            }


        }

        Date fromDate = null, toDate = null, dateOfPayment = null;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(billDto.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(billDto.getToDate());
            if(billDto.getDateOfPayment()!=null){
                dateOfPayment = new SimpleDateFormat("yyyy-MM-dd").parse(billDto.getDateOfPayment());
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        bill.setApartment(apartment);
        bill.setToDate(toDate);
        bill.setFromDate(fromDate);
        bill.setDateOfPayment(dateOfPayment);
        bill.setTotalPrice(TotalPrice);
        bill.setElectricConsumption(billDto.getElectricConsumption());
        bill.setWaterConsumption(billDto.getWaterConsumption());



        Bill billResponse = billService.save(bill);


        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Update bill successfully!")
                .data(billResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }
    @GetMapping("")
    public ResponseEntity<?> getAllBill(){
        List<Bill> bill = (List<Bill>) billService.findAll();
        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find bill successfully!")
                .data(bill)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable Long id){
        Optional<Bill> bill =  billService.findById(id);
        ResponseObject responseObject = ResponseObject.builder()
                .status("ok")
                .message("Find bill successfully!")
                .data(bill.get())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

}
