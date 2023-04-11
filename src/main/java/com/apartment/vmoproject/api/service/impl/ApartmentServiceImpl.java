package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.exception.NotFoundException;
import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.repository.ApartmentRepository;
import com.apartment.vmoproject.api.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Override
    public <S extends Apartment> S save(S entity) {
        return apartmentRepository.save(entity);
    }

    @Override
    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("Not found Apartment "+ id);
        });
    }

    @Override
    public Apartment updateApartment(Apartment apartment, Long id){
        Optional<Apartment> apartmentOld = apartmentRepository.findById(id);
        if(apartmentOld==null){
            throw new NotFoundException("Not found Apartment "+ id);
        }
        apartment.setId(id);
        return apartmentRepository.save(apartment);


    }

    @Override
    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }


    @Override
    public long count() {
        return apartmentRepository.count();
    }


    @Override
    public void delete(Apartment entity) {
        apartmentRepository.delete(entity);
    }



    @Override
    public Page<Apartment> findByNumberContainingWithPagination(int pagenumber, int pageSize, Integer number) {
        return apartmentRepository.findByNumberContaining(PageRequest.of(pagenumber,pageSize), number);
    }

    @Override
    public Page<Apartment> findByNumberContainingAndAndStatusWithPagination(int pagenumber, int pageSize, String number, Boolean status) {
        return apartmentRepository.findByNumberContainingAndAndStatus(PageRequest.of(pagenumber,pageSize), number, status);
    }


    @Override
    public Page<Apartment> findProductWithPagination(int offset, int pageSize) {
        return apartmentRepository.findAll(PageRequest.of(offset,pageSize));
    }
}
