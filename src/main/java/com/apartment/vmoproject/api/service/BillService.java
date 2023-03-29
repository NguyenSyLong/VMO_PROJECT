package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Bill;

import java.util.Optional;

public interface BillService {
    <S extends Bill> S save(S entity);

    <S extends Bill> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Bill> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<Bill> findAll();

    Iterable<Bill> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Bill entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Bill> entities);

    void deleteAll();
}
