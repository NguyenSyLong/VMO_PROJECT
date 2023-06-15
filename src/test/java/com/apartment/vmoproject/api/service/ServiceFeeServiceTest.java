package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Service_Fee;
import com.apartment.vmoproject.api.repository.Service_FeeRepository;
import com.apartment.vmoproject.api.service.impl.Service_FeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class ServiceFeeServiceTest {

    @Mock
    Service_FeeRepository service_feeRepository;

    @InjectMocks
    Service_FeeService service_feeService = new Service_FeeServiceImpl();


    @Test
    public void testFindALlAndIdAndName(){

        Service_Fee service_fee = Service_Fee.builder().name("WaterExpense").unitPrice(120000F).id(1L).build();
        Service_Fee service_fee1 = Service_Fee.builder().name("ElectricExpense").unitPrice(120000F).id(2L).build();
        Service_Fee service_fee2 = Service_Fee.builder().name("CleaningExpense").unitPrice(120000F).id(3L).build();
        Service_Fee service_fee3 = Service_Fee.builder().name("FixingExpense").unitPrice(120000F).id(4L).build();

        List<Service_Fee> service_feeList = List.of(service_fee,service_fee1,service_fee2,service_fee3);

        when(service_feeRepository.findAll()).thenReturn(service_feeList);

        List<Service_Fee>  findAll  = (List<Service_Fee>) service_feeService.findAll();

        assertEquals(4,findAll.size());


        when(service_feeRepository.findById(1L)).thenReturn(Optional.of(service_fee));

        Optional<Service_Fee> byId = service_feeService.findById(1L);

        assertEquals(1, byId.get().getId().longValue());

        Page<Service_Fee> service_feePage = new PageImpl<>(service_feeList) ;


        when(service_feeRepository.findService_FeeByNameContaining(PageRequest.of(0,4),"Expense")).thenReturn(service_feePage);

        Page<Service_Fee> expense = service_feeService.findService_FeeByName(0, 4, "Expense");

        assertEquals(4,expense.getTotalElements());
    }

    @Test
    public void testSave(){

        Service_Fee service_fee3 = Service_Fee.builder().name("FixingExpense").unitPrice(120000F).id(4L).build();
        when(service_feeRepository.save(service_fee3)).thenReturn(service_fee3);

        Service_Fee save = service_feeService.save(service_fee3);

        assertEquals("FixingExpense",save.getName());
        assertEquals(4,save.getId().longValue());

    }




}
