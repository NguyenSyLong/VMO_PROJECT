package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.repository.DwellerRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DwellerService {

    <S extends Dweller> S save(S entity);

    Optional<Dweller> findById(Long aLong);

    Iterable<Dweller> findAll();

    long count();

    void delete(Dweller entity);

    Page<Dweller> findByNameContainingWithPagination(int pageNumber, int pageSize, String name);

    Page<Dweller> findByNameContainingAndStatusWithPagination(int pageNumber, int pageSize, String name, Boolean status);
}
