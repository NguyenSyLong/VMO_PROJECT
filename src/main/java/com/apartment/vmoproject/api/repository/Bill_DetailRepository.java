package com.apartment.vmoproject.api.repository;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Bill_Detail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Bill_DetailRepository extends CrudRepository<Bill_Detail,Long> {
    List<Bill_Detail> findByBill(Bill bill);
//    @Modifying      // to mark delete or update query
//    @Query(value = "DELETE FROM Bill_Detail e WHERE e.id = :id")
//    void deleteByBillId(@Param("id") Long id);

}
