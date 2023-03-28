package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Apartment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApartmentService {

    <S extends Apartment> S save(S entity);

    <S extends Apartment> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Apartment> findById(Long aLong);

    boolean existsById(Long aLong);

    List<Apartment> findAll();

    Iterable<Apartment> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Apartment entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Apartment> entities);

    void deleteAll();
}
