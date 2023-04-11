package com.apartment.vmoproject.api;




import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.repository.ApartmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VmoProjectApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(VmoProjectApplicationTests.class.getName());

    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    public void demoTest(){
        LOGGER.info("Aparment: {}", apartmentRepository);
        // Arrange
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("104")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .numOfRoom(1).status(true).build();
        apartmentRepository.save(apartment1);

        List<Apartment> apartments = apartmentRepository.findAll();

        assertEquals(1, apartments.size());
        assertTrue(true);
    }

}
