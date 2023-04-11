package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.exception.NotFoundException;
import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.service.ApartmentService;
import com.apartment.vmoproject.api.service.impl.ApartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class ApartmentServiceTest {

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentService apartmentService = new ApartmentServiceImpl();


    @Test
    public void testFindAllAndFindByNumberOrStatus() {
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(1L)
                .numOfRoom(1).status(false).build();

        Apartment apartment2 = Apartment
                .builder().name("căn hộ 2")
                .number("102")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(2L)
                .numOfRoom(4).status(true).build();

        Apartment apartment3 = Apartment
                .builder().name("căn hộ 3")
                .number("103")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(3L)
                .numOfRoom(4).status(true).build();

        Apartment apartment4 = Apartment
                .builder().name("căn hộ 4")
                .number("104")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(4L)
                .numOfRoom(4).status(true).build();
        Apartment apartment5 = Apartment
                .builder().name("căn hộ 5")
                .number("99")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(5L)
                .numOfRoom(4).status(true).build();
        // find all and find by id
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(apartment1);
        apartments.add(apartment2);
        apartments.add(apartment3);
        apartments.add(apartment4);
        apartments.add(apartment5);

        when(apartmentRepository.findAll())
                .thenReturn(apartments);
        when(apartmentRepository.findById(1L))
                .thenReturn(Optional.of(apartment1));


        List<Apartment> apartmentRes = apartmentService.findAll();
        assertNotNull(apartmentRes);
        assertEquals(apartmentRes.size(), 5);


        Apartment apartmentResById = apartmentService.findById(1L);
        assertNotNull(apartmentResById);
        assertEquals(apartmentResById.getId().longValue(), 1);

        //test findByNumberContainingWithPagination
        List<Apartment> apartments1 = List.of(apartment1, apartment2, apartment3, apartment4);
        Page<Apartment> pA = new PageImpl<>(apartments1);

        when(apartmentRepository.findByNumberContaining(PageRequest.of(0, 5), 10)).thenReturn(pA);

        Page<Apartment> pApartment = apartmentService.findByNumberContainingWithPagination(0, 5, 10);
        assertNotNull(pApartment);
        assertEquals(pApartment.getTotalElements(), 4);


        //test findByNumberContainingAndAndStatusWithPagination
        List<Apartment> apartments2 = List.of(apartment1, apartment2, apartment3, apartment4);
        Page<Apartment> pA1 = new PageImpl<>(apartments2);

        when(apartmentRepository.findByNumberContainingAndAndStatus(PageRequest.of(0, 5), "10", true)).thenReturn(pA1);

        Page<Apartment> pApartment1 = apartmentService.findByNumberContainingAndAndStatusWithPagination(0, 5, "10", true);
        assertNotNull(pApartment1);
        assertEquals(pApartment1.getTotalElements(), 4);

    }

    @Test
    public void testSave() {
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(1L)
                .numOfRoom(1).status(false).build();
        when(apartmentRepository.save(apartment1)).thenReturn(apartment1);

        Apartment apartment = apartmentService.save(apartment1);

        assertNotNull(apartment);
        assertEquals(apartment.getId().longValue(), 1);
        assertEquals(apartment.getArea().longValue(), 12);
        assertEquals(apartment.getPrice().longValue(), 12233333);
        assertEquals(apartment.getDescription(), "long");
        assertEquals(apartment.getName(), "căn hộ 1");

    }



    @Test
    public void testUpdate(){
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(1L)
                .numOfRoom(1).status(false).build();
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment1));


        Apartment apartment2 = Apartment
                .builder().name("căn hộ 2")
                .number("102")
                .area(Float.valueOf(123))
                .price(Long.valueOf(12233334))
                .description("nam")
                .id(1L)
                .numOfRoom(1).status(false).build();

        when(apartmentRepository.save(apartment2)).thenReturn(apartment2);

        Apartment apartment3 = apartmentService.updateApartment(apartment2,1L);
        assertNotNull(apartment3);
        assertEquals(apartment3.getId().longValue(), 1);
        assertEquals(apartment3.getArea().longValue(), 123);
        assertEquals(apartment3.getPrice().longValue(), 12233334);
        assertEquals(apartment3.getDescription(), "nam");
        assertEquals(apartment3.getName(), "căn hộ 2");




    }


}
