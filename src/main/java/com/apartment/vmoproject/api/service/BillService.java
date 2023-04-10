package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Bill;

import java.util.Optional;

public interface BillService {
    <S extends Bill> S save(S entity);

    Optional<Bill> findById(Long aLong);

    Iterable<Bill> findAll();

    long count();

    void delete(Bill entity);

}
