package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Bill_Detail;

import java.util.List;
import java.util.Optional;

public interface Bill_DetailService {

    <S extends Bill_Detail> S save(S entity);

    List<Bill_Detail> findByBill(Bill bill);

    Optional<Bill_Detail> findById(Long aLong);


    Iterable<Bill_Detail> findAll();

    long count();

    void delete(Bill_Detail entity);

}
