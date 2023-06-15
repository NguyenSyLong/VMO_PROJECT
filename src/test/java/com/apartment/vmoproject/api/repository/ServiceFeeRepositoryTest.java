package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Service_Fee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Transactional
public class ServiceFeeRepositoryTest {

    @Autowired
    Service_FeeRepository service_feeRepository;

    @BeforeEach
    public void setUpServiceFee(){
        Service_Fee service_fee = Service_Fee.builder().name("WaterExpense").unitPrice(120000F).build();
        Service_Fee service_fee1 = Service_Fee.builder().name("ElectricExpense").unitPrice(120000F).build();
        Service_Fee service_fee2 = Service_Fee.builder().name("CleaningExpense").unitPrice(120000F).build();
        Service_Fee service_fee3 = Service_Fee.builder().name("FixingExpense").unitPrice(120000F).build();

        service_feeRepository.save(service_fee);
        service_feeRepository.save(service_fee1);
        service_feeRepository.save(service_fee2);
        service_feeRepository.save(service_fee3);

    }
    @AfterEach
    public void setUpDeleteAll(){
        service_feeRepository.deleteAll();
    }


    @Test
    public void testFindAll(){
        List<Service_Fee> all = (List<Service_Fee>) service_feeRepository.findAll();

        assertEquals(4,all.size());


    }

    @Test
    public void testFindById(){
        Optional<Service_Fee> service_fee =  service_feeRepository.findById(1L);

        assertNotNull(service_fee);

        List<Long> list = List.of(1L,2L);
        List<Service_Fee> service_feeList = service_feeRepository.findByIdIsIn(list);

        assertNotNull(service_feeList);

    }


    @Test
    public void testSave(){
        Service_Fee service_fee3 = Service_Fee.builder().name("AIRCExpense").unitPrice(120000F).build();

        Service_Fee save = service_feeRepository.save(service_fee3);

        assertEquals("AIRCExpense",save.getName());
    }


}
