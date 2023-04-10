package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwellerRepository extends CrudRepository<Dweller,Long> {
    List<Dweller> findByApartment(Apartment apartment);
    Page<Dweller> findByNameContaining(PageRequest pageRequest, String name);
    Page<Dweller> findByNameContainingAndStatus(PageRequest pageRequest, String name, Boolean status);
}
