package com.xavier.fleet.service;


import com.xavier.fleet.model.City;
import com.xavier.fleet.repository.CityRepository;
import com.xavier.fleet.service.exception.CityExistException;
import com.xavier.fleet.service.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public CityService(@Autowired CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City save(final City city) {
        verifyIfExistCity(city);
        return cityRepository.save(city);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Integer id) {
        verifyIfNotExistCity(id);
        return cityRepository.getOne(id);
    }

    public List<City> findByProvince(Integer provinceId) {
        return cityRepository.findByProvinceProvinceId(provinceId);
    }

    public void delete(Integer id) {
        verifyIfNotExistCity(id);
        cityRepository.deleteById(id);
    }

    private void verifyIfNotExistCity(Integer id) throws CityNotFoundException {
        Optional<City> foundCity = cityRepository.findById(id);
        if (!foundCity.isPresent()) {
            throw  new CityNotFoundException();
        }
    }

    private void verifyIfExistCity(City city) {
        Optional<City> foundCity = cityRepository.findByCityName(city.getCityName());
        if (foundCity.isPresent() && (city.isNew() || isUpdatingToADifferentCity(city, foundCity))) {
            throw new CityExistException();
        }
    }

    private boolean isUpdatingToADifferentCity(City city, Optional<City> foundCity) {
        return city.exist() && !(city.equals(foundCity.get()));
    }
}
