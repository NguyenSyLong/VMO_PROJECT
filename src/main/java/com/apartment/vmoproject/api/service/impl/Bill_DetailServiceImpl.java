package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Bill_Detail;
import com.apartment.vmoproject.api.repository.Bill_DetailRepository;
import com.apartment.vmoproject.api.service.Bill_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Bill_DetailServiceImpl implements Bill_DetailService {

    @Autowired
    private Bill_DetailRepository bill_detailRepository;

    @Override
    public <S extends Bill_Detail> S save(S entity) {
        return bill_detailRepository.save(entity);
    }

    @Override
    public <S extends Bill_Detail> Iterable<S> saveAll(Iterable<S> entities) {
        return bill_detailRepository.saveAll(entities);
    }

    @Override
    public Optional<Bill_Detail> findById(Long aLong) {
        return bill_detailRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return bill_detailRepository.existsById(aLong);
    }

    @Override
    public Iterable<Bill_Detail> findAll() {
        return bill_detailRepository.findAll();
    }

    @Override
    public Iterable<Bill_Detail> findAllById(Iterable<Long> longs) {
        return bill_detailRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return bill_detailRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        bill_detailRepository.deleteById(aLong);
    }

    @Override
    public void delete(Bill_Detail entity) {
        bill_detailRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        bill_detailRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Bill_Detail> entities) {
        bill_detailRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        bill_detailRepository.deleteAll();
    }
}
