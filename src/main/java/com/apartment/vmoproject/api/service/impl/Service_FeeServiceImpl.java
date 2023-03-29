package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Service_Fee;
import com.apartment.vmoproject.api.repository.Service_FeeRepository;
import com.apartment.vmoproject.api.service.Service_FeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public <S extends Service_Fee> Iterable<S> saveAll(Iterable<S> entities) {
        return service_feeRepository.saveAll(entities);
    }

    @Override
    public Optional<Service_Fee> findById(Long aLong) {
        return service_feeRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return service_feeRepository.existsById(aLong);
    }

    @Override
    public Iterable<Service_Fee> findAll() {
        return service_feeRepository.findAll();
    }

    @Override
    public Iterable<Service_Fee> findAllById(Iterable<Long> longs) {
        return service_feeRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return service_feeRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        service_feeRepository.deleteById(aLong);
    }

    @Override
    public void delete(Service_Fee entity) {
        service_feeRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        service_feeRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Service_Fee> entities) {
        service_feeRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        service_feeRepository.deleteAll();
    }
}
