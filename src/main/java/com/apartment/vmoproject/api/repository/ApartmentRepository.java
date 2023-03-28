package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment,Long> {
    List<Apartment> findAll();

}
