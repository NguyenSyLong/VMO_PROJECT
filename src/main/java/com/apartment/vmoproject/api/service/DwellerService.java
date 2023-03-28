package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.repository.DwellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DwellerService {

    <S extends Dweller> S save(S entity);

    <S extends Dweller> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Dweller> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<Dweller> findAll();

    Iterable<Dweller> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Dweller entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Dweller> entities);

    List<Dweller> findByApartment(Apartment apartment);

    void deleteAll();
}
