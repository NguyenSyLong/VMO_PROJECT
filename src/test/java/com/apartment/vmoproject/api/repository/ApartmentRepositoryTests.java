package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Apartment;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@Transactional
class ApartmentRepositoryTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentRepositoryTests.class.getName());

    @Autowired
    ApartmentRepository apartmentRepository;

    @BeforeEach
    public void setUpApartment() {
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(1).status(false).build();

        Apartment apartment2 = Apartment
                .builder().name("căn hộ 2")
                .number("102")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(4).status(true).build();

        Apartment apartment3 = Apartment
                .builder().name("căn hộ 3")
                .number("103")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(4).status(true).build();

        Apartment apartment4 = Apartment
                .builder().name("căn hộ 4")
                .number("104")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(4).status(true).build();

        apartmentRepository.save(apartment1);
        apartmentRepository.save(apartment2);
        apartmentRepository.save(apartment3);
        apartmentRepository.save(apartment4);
    }

    @AfterEach
    public void setUpAfterTest(){
        apartmentRepository.deleteAll();
    }

    @Test
    public void testFindByID() {

        Optional<Apartment> apartment = apartmentRepository.findById(1L);

        assertNotNull(apartment);

    }


    @Test
    public void TestFindAll() {

        List<Apartment> apartments = apartmentRepository.findAll();

        assertEquals(4, apartments.size());


    }

    @Test
    public void testSave() {
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 5")
                .number("105")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(1).status(true).build();
        Apartment a = apartmentRepository.save(apartment1);
        assertNotNull(a);
    }



    @Test
    public void testFindByNumberContaining(){
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 5")
                .number("99")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(1).status(true).build();
        Page<Apartment> apartments = apartmentRepository.findByNumberContaining(PageRequest.of(0,4),10);

        assertEquals(apartments.getTotalElements(),4);
    }

    @Test
    public void testFindByNumberContainingAndStatus(){
        Page<Apartment> apartments = apartmentRepository.findByNumberContainingAndAndStatus(PageRequest.of(0,4),"10",false);

        assertEquals(apartments.getTotalElements(),1);
    }

}
