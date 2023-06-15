package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.repository.ApartmentRepository;
import com.apartment.vmoproject.api.repository.DwellerRepository;
import com.apartment.vmoproject.api.service.DwellerService;
import com.apartment.vmoproject.api.service.impl.DwellerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class DwellerServiceTest {
    @Mock
    private DwellerRepository dwellerRepository;

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private DwellerService dwellerService = new DwellerServiceImpl();


    @Test
    public void testFindAllAndFindByNameOrStatus() throws ParseException {
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(1L)
                .numOfRoom(1).status(true).build();

        Apartment apartment2 = Apartment
                .builder().name("căn hộ 2")
                .number("102")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(2L)
                .numOfRoom(4).status(true).build();


        Dweller dweller = Dweller.builder()
                .name("long")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartment1)
                .isRepresent(true)
                .id(1L)
                .build();
        Dweller dweller2 = Dweller.builder()
                .name("nam")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartment2)
                .isRepresent(true)
                .id(2L)
                .build();

        List<Dweller> dwellerlist = new ArrayList<>();
        dwellerlist.add(dweller);
        dwellerlist.add(dweller2);
        Iterable<Dweller> dwellers = dwellerlist;
        when(dwellerRepository.findAll()).thenReturn(dwellers);

        when(dwellerRepository.findById(1L)).thenReturn(Optional.of(dweller));


        List<Dweller> dwellersfindAll = (List<Dweller>) dwellerRepository.findAll();
        Optional<Dweller> dwellerFindById = dwellerRepository.findById(1L);



        assertNotNull(dwellersfindAll);
        assertEquals(2,dwellersfindAll.size());


        assertNotNull(dwellerFindById);
        assertEquals(1, dwellerFindById.get().getId().longValue() );


        List<Dweller> dwellersPage = List.of(dweller,dweller2);
        Page<Dweller> pA1 = new PageImpl<>(dwellersPage);

        when(dwellerRepository.findByNameContaining(PageRequest.of(0,3),"long")).thenReturn(pA1);

        Page<Dweller> pageFindName = dwellerService.findByNameContainingWithPagination(0,3,"long");

        assertNotNull(pageFindName);
        assertEquals(2,pageFindName.getTotalElements());


        List<Dweller> dwellerPage2 = List.of(dweller,dweller2);
        Page<Dweller> pA2 = new PageImpl<>(dwellersPage);

        when(dwellerRepository.findByNameContainingAndStatus(PageRequest.of(0,3),"long",true)).thenReturn(pA2);

        Page<Dweller> pageFindNameAndStatus = dwellerService.findByNameContainingAndStatusWithPagination(0,3,"long", true);

        assertNotNull(pageFindNameAndStatus);
        assertEquals(2,pageFindNameAndStatus.getTotalElements());



    }

    @Test
    public void testSave() throws ParseException {

        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(1L)
                .numOfRoom(1).status(true).build();
        Dweller dweller = Dweller.builder()
                .name("long")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartment1)
                .isRepresent(true)
                .id(1L)
                .build();

        when(dwellerRepository.save(dweller)).thenReturn(dweller);
        Dweller dweller1 = dwellerService.save(dweller);

        assertNotNull(dweller1);
        assertEquals(dweller1.getId().longValue(), 1);
        assertEquals(dweller1.getName(), "long");
        assertEquals(dweller1.getEmail(), "longhthththt@gmail.com");
        assertEquals(dweller1.getFrontSideImage(), "hhttps://image/1");
        assertEquals(dweller1.getBackSideImage(), "hhttps://image/1");
        assertEquals(dweller1.getApartment(),apartment1);
        assertEquals(dweller1.getDateOfBirth(),new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"));
        assertEquals(dweller1.getGender(),true);
        assertEquals(dweller1.getStatus(),true);
        assertEquals(dweller1.getIsRepresent(),true);

    }

    @Test
    public void testUpdate() throws ParseException {
        Apartment apartment1 = Apartment
                .builder().name("căn hộ 1")
                .number("101")
                .area(Float.valueOf(12))
                .price(Long.valueOf(12233333))
                .description("long")
                .id(1L)
                .numOfRoom(1).status(true).build();
        Dweller dweller = Dweller.builder()
                .name("long")
                .email("longhthththt@gmail.com")
                .frontSideImage("hhttps://image/1")
                .backSideImage("hhttps://image/1")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"))
                .gender(true)
                .status(true)
                .apartment(apartment1)
                .isRepresent(true)
                .id(1L)
                .build();

        when(dwellerService.findById(1L)).thenReturn(Optional.of(dweller));


        Dweller dweller2 = Dweller.builder()
                .name("linh")
                .email("linh@gmail.com")
                .frontSideImage("hhttps://image/2")
                .backSideImage("hhttps://image/2")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-02"))
                .gender(false)
                .status(true)
                .apartment(apartment1)
                .isRepresent(true)
                .id(1L)
                .build();

//        whem(dwellerService.save(dweller2)).the
//
//        assertNotNull(dweller1);
//        assertEquals(dweller1.getId().longValue(), 1);
//        assertEquals(dweller1.getName(), "long");
//        assertEquals(dweller1.getEmail(), "longhthththt@gmail.com");
//        assertEquals(dweller1.getFrontSideImage(), "hhttps://image/1");
//        assertEquals(dweller1.getBackSideImage(), "hhttps://image/1");
//        assertEquals(dweller1.getApartment(),apartment1);
//        assertEquals(dweller1.getDateOfBirth(),new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-03"));
//        assertEquals(dweller1.getGender(),true);
//        assertEquals(dweller1.getStatus(),true);
//        assertEquals(dweller1.getIsRepresent(),true);



    }
}
