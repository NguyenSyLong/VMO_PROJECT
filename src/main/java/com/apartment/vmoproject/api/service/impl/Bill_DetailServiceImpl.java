package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Bill_Detail;
import com.apartment.vmoproject.api.repository.Bill_DetailRepository;
import com.apartment.vmoproject.api.service.Bill_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Bill_Detail> findByBill(Bill bill) {
        return bill_detailRepository.findByBill(bill);
    }

    @Override
    public Optional<Bill_Detail> findById(Long aLong) {
        return bill_detailRepository.findById(aLong);
    }

    @Override
    public Iterable<Bill_Detail> findAll() {
        return bill_detailRepository.findAll();
    }

    @Override
    public long count() {
        return bill_detailRepository.count();
    }

    @Override
    public void delete(Bill_Detail entity) {
        bill_detailRepository.delete(entity);
    }

}
