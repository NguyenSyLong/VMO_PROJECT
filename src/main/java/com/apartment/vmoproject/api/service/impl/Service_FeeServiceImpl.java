package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Service_Fee;
import com.apartment.vmoproject.api.repository.Service_FeeRepository;
import com.apartment.vmoproject.api.service.Service_FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service_FeeServiceImpl implements Service_FeeService {

    @Override
    public List<Service_Fee> findByIdIsIn(List<Long> ids) {
        return service_feeRepository.findByIdIsIn(ids);
    }

    @Autowired
    private Service_FeeRepository  service_feeRepository;

    @Override
    public <S extends Service_Fee> S save(S entity) {
        return service_feeRepository.save(entity);
    }


    @Override
    public Optional<Service_Fee> findById(Long aLong) {
        return service_feeRepository.findById(aLong);
    }


    @Override
    public Iterable<Service_Fee> findAll() {
        return service_feeRepository.findAll();
    }


    @Override
    public long count() {
        return service_feeRepository.count();
    }


    @Override
    public void delete(Service_Fee entity) {
        service_feeRepository.delete(entity);
    }

    @Override
    public Page<Service_Fee> findService_FeeByName(int pageNumber, int pageSize, String name) {
        return service_feeRepository.findService_FeeByNameContaining(PageRequest.of(pageNumber,pageSize), name);
    }
}
