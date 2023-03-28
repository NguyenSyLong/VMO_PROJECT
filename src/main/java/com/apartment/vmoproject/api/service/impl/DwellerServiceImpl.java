package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.model.Dweller;
import com.apartment.vmoproject.api.repository.DwellerRepository;
import com.apartment.vmoproject.api.service.DwellerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public <S extends Dweller> Iterable<S> saveAll(Iterable<S> entities) {
        return dwellerRepository.saveAll(entities);
    }

    @Override
    public Optional<Dweller> findById(Long aLong) {
        return dwellerRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return dwellerRepository.existsById(aLong);
    }

    @Override
    public Iterable<Dweller> findAll() {
        return dwellerRepository.findAll();
    }

    @Override
    public Iterable<Dweller> findAllById(Iterable<Long> longs) {
        return dwellerRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return dwellerRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        dwellerRepository.deleteById(aLong);
    }

    @Override
    public void delete(Dweller entity) {
        dwellerRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        dwellerRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Dweller> entities) {
        dwellerRepository.deleteAll(entities);
    }

    @Override
    public List<Dweller> findByApartment(Apartment apartment) {
        return dwellerRepository.findByApartment(apartment);
    }

    @Override
    public void deleteAll() {
        dwellerRepository.deleteAll();
    }


}
