package com.apartment.vmoproject.api.repository;


import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Bill;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@Transactional
public class BillRepositoryTest {
    @Autowired
    BillRepository billRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @BeforeEach
    public void setUpTest(){
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(1).status(false).build();

        Date fromDate = null, toDate = null, dateOfPayment = null;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-05");
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-08");
            dateOfPayment = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-10");

        } catch (ParseException e) {
            e.printStackTrace();
        }



        Bill bill = Bill.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .totalPrice(1000000L)
                .dateOfPayment(dateOfPayment)
                .waterConsumption(2L)
                .electricConsumption(3L)
                .apartment(apartment1).build();

        Bill bill2 = Bill.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .totalPrice(1000000L)
                .dateOfPayment(dateOfPayment)
                .waterConsumption(2L)
                .electricConsumption(3L)
                .apartment(apartment1).build();


        billRepository.save(bill);
        billRepository.save(bill2);

    }

    @Test
    public void testFindAll(){
        List<Bill> all = (List<Bill>) billRepository.findAll();

        assertEquals(2,all.size());
    }

    @Test
    public void testFindById(){
        Optional<Bill> bill = billRepository.findById(1L);
        assertNotNull(bill);
    }

    @Test
    public void testSave(){
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(1).status(false).build();

        Date fromDate = null, toDate = null, dateOfPayment = null;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-05");
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-08");
            dateOfPayment = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-10");

        } catch (ParseException e) {
            e.printStackTrace();
        }



        Bill bill = Bill.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .totalPrice(1000000L)
                .dateOfPayment(dateOfPayment)
                .waterConsumption(2L)
                .electricConsumption(3L)
                .apartment(apartment1).build();

        Bill save = billRepository.save(bill);

        assertNotNull(save);

    }



}
