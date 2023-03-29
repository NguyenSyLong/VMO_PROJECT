package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill,Long> {
}
