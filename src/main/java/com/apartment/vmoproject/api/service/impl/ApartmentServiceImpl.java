package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Apartment;
import com.apartment.vmoproject.api.repository.ApartmentRepository;
import com.apartment.vmoproject.api.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public <S extends Apartment> Iterable<S> saveAll(Iterable<S> entities) {
        return apartmentRepository.saveAll(entities);
    }

    @Override
    public Optional<Apartment> findById(Long aLong) {
        return apartmentRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return apartmentRepository.existsById(aLong);
    }

    @Override
    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Iterable<Apartment> findAllById(Iterable<Long> longs) {
        return apartmentRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return apartmentRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        apartmentRepository.deleteById(aLong);
    }

    @Override
    public void delete(Apartment entity) {
        apartmentRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        apartmentRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Apartment> entities) {
        apartmentRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        apartmentRepository.deleteAll();
    }
}
