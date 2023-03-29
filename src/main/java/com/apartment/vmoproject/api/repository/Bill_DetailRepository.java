package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Bill_Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bill_DetailRepository extends CrudRepository<Bill_Detail,Long> {
}
