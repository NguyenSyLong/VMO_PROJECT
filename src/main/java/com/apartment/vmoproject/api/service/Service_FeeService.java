package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Service_Fee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface Service_FeeService {
    List<Service_Fee> findByIdIsIn(List<Long> ids);

    <S extends Service_Fee> S save(S entity);

    Optional<Service_Fee> findById(Long aLong);

    Iterable<Service_Fee> findAll();

    long count();

    void delete(Service_Fee entity);

    Page<Service_Fee> findService_FeeByName(int pageNumber, int pageSize, String name);

}
