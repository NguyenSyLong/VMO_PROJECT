package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.repository.BillRepository;
import com.apartment.vmoproject.api.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public <S extends Bill> S save(S entity) {
        return billRepository.save(entity);
    }

    @Override
    public <S extends Bill> Iterable<S> saveAll(Iterable<S> entities) {
        return billRepository.saveAll(entities);
    }

    @Override
    public Optional<Bill> findById(Long aLong) {
        return billRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return billRepository.existsById(aLong);
    }

    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Iterable<Bill> findAllById(Iterable<Long> longs) {
        return billRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return billRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        billRepository.deleteById(aLong);
    }

    @Override
    public void delete(Bill entity) {
        billRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        billRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Bill> entities) {
        billRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        billRepository.deleteAll();
    }
}
