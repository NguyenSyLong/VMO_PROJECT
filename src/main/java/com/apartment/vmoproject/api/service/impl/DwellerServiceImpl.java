package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.repository.DwellerRepository;
import com.apartment.vmoproject.api.service.DwellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DwellerServiceImpl implements DwellerService {
    @Autowired
    DwellerRepository dwellerRepository;

    @Override
    public <S extends Dweller> S save(S entity) {
        return dwellerRepository.save(entity);
    }

    @Override
    public Optional<Dweller> findById(Long aLong) {
        return dwellerRepository.findById(aLong);
    }

    @Override
    public Iterable<Dweller> findAll() {
        return dwellerRepository.findAll();
    }

    @Override
    public long count() {
        return dwellerRepository.count();
    }


    @Override
    public void delete(Dweller entity) {
        dwellerRepository.delete(entity);
    }


    @Override
    public Page<Dweller> findByNameContainingWithPagination(int pageNumber, int pageSize, String name) {
        return dwellerRepository.findByNameContaining(PageRequest.of(pageNumber,pageSize),name);
    }

    @Override
    public Page<Dweller> findByNameContainingAndStatusWithPagination(int pageNumber, int pageSize, String name, Boolean status) {
        return dwellerRepository.findByNameContainingAndStatus(PageRequest.of(pageNumber,pageSize), name, status);
    }
}
