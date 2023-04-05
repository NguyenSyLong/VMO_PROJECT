package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Bill_Detail;

import java.util.List;
import java.util.Optional;

public interface Bill_DetailService {

    <S extends Bill_Detail> S save(S entity);

    List<Bill_Detail> findByBill(Bill bill);

    <S extends Bill_Detail> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Bill_Detail> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<Bill_Detail> findAll();

    Iterable<Bill_Detail> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Bill_Detail entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Bill_Detail> entities);

    void deleteAll();
}
