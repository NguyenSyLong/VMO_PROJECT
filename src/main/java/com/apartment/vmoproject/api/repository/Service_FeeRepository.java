package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Service_Fee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Service_FeeRepository extends CrudRepository<Service_Fee,Long> {

}
