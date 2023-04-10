package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Bill_Detail;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.model.Service_Fee;
import com.apartment.vmoproject.api.repository.BillRepository;
import com.apartment.vmoproject.api.service.BillService;
import com.apartment.vmoproject.api.service.DwellerService;
import com.apartment.vmoproject.api.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public <S extends Bill> S save(S entity) {
        return billRepository.save(entity);
    }


    @Override
    public Optional<Bill> findById(Long aLong) {
        return billRepository.findById(aLong);
    }

    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public long count() {
        return billRepository.count();
    }


    @Override
    public void delete(Bill entity) {
        billRepository.delete(entity);
    }



//    @Scheduled(cron = "0 0 0 30 * *")
    public void sendMailBill(){
        List<Bill> bills = (List<Bill>) billRepository.findAll();



        for (Bill bill :bills) {
            List<Service_Fee> service_feeList = new ArrayList<>();
            for (Bill_Detail bd: bill.getBill_details()) {
                service_feeList.add(bd.getService_fee());
            }

            if(bill.getDateOfPayment()==null&&bill.getApartment().getStatus()==true){
                for (Dweller dweller: bill.getApartment().getDwellers()) {
                    if(dweller.getIsRepresent() == true && dweller.getStatus() == true){
                        emailSenderService.sendEmail(dweller.getEmail(), service_feeList, bill);
                    }
                }

            }

        }
    }

}
