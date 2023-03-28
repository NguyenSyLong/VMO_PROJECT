package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Service_Fee;

import java.util.Optional;

public interface Service_FeeService {
    <S extends Service_Fee> S save(S entity);

    <S extends Service_Fee> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Service_Fee> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<Service_Fee> findAll();

    Iterable<Service_Fee> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Service_Fee entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Service_Fee> entities);

    void deleteAll();
}
