package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Service_Fee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Service_FeeRepository extends CrudRepository<Service_Fee,Long> {
    List<Service_Fee> findByIdIsIn(List<Long> ids);
    Page<Service_Fee> findService_FeeByNameContaining(PageRequest pageRequest, String name);
}
