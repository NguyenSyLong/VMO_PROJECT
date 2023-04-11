package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Apartment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApartmentService {

    <S extends Apartment> S save(S entity);

    Apartment findById(Long aLong);

    Apartment updateApartment(Apartment apartment, Long id);

    List<Apartment> findAll();

    long count();

    void delete(Apartment entity);

    Page<Apartment> findByNumberContainingWithPagination(int pagenumber, int pageSize, Integer number);

    Page<Apartment> findByNumberContainingAndAndStatusWithPagination(int pagenumber, int pageSize, String number, Boolean status);

    Page<Apartment> findProductWithPagination(int offset, int pageSize);
}
