package com.apartment.vmoproject.api.repository;


import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class DwellerRepositoryTest {

    @Autowired
    DwellerRepository dwellerRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @BeforeEach
    public void setUpBeforeTest() throws ParseException {
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
        Apartment apartmentSave = apartmentRepository.save(apartment1);
        Apartment apartmentSave1 = apartmentRepository.save(apartment2);



        String date = "2000-04-03";
        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(date);



        Dweller dweller = Dweller.builder()
                .name("long")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartmentSave)
                .isRepresent(true)
                .build();
        Dweller dweller2 = Dweller.builder()
                .name("nam")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartmentSave1)
                .isRepresent(true)
                .build();

        dwellerRepository.save(dweller);
        dwellerRepository.save(dweller2);

    }
    @AfterEach
    public void setUpAfterTest(){
        apartmentRepository.deleteAll();
        dwellerRepository.deleteAll();
    }

    @Test
    public void testFindAll(){
        List<Dweller> dwellers = (List<Dweller>) dwellerRepository.findAll();

        assertEquals(dwellers.size(),2);
    }


    @Test
    public void testFindById(){
        Optional<Dweller> dwellers = dwellerRepository.findById(1L);

        assertNotNull(dwellers);
    }

    @Test
    public void testSave() throws ParseException {
        List<Apartment> apartment = apartmentRepository.findAll();
        Dweller dweller1 = Dweller.builder()
                .name("nam")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartment.get(0))
                .isRepresent(true)
                .build();

        Dweller dweller2 = dwellerRepository.save(dweller1);
        assertNotNull(dweller2);
    }

    @Test
    public void testFindByNameContaining(){
        Page<Dweller> dwellers = dwellerRepository.findByNameContaining(PageRequest.of(0,2),"long");

        assertEquals(dwellers.getTotalElements(),1);
    }

    @Test
    public void findByNameContainingAndStatus(){
        Page<Dweller> dwellers = dwellerRepository.findByNameContainingAndStatus(PageRequest.of(0,2),"long",true);

        assertEquals(dwellers.getTotalElements(),1);
    }

}
