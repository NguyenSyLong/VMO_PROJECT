package com.apartment.vmoproject.api;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.repository.ApartmentRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class ApartmentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentRepositoryTest.class.getName());
    @Autowired
    private ApartmentRepository apartmentRepository;



    @Test
    public void testFindAll() {
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

    }
}
