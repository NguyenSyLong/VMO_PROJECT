package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment,Long> {
    List<Apartment> findAll();
    Apartment findApartmentById(Long id);
    Page<Apartment> findByNumberContaining(PageRequest pageRequest, Integer number);
    Page<Apartment> findByNumberContainingAndAndStatus(PageRequest pageRequest, String number, Boolean status);
}
