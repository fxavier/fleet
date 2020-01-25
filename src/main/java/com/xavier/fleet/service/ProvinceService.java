package com.xavier.fleet.service;


import com.xavier.fleet.model.Province;
import com.xavier.fleet.repository.ProvinceRepository;
import com.xavier.fleet.service.exception.ProvinceExistException;
import com.xavier.fleet.service.exception.ProvinceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public ProvinceService(@Autowired ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public Province save(final Province province) {
        verifyIfProvinceExists(province);
        return provinceRepository.save(province);

    }

    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    public Province findById(Integer id) {
        verifyIfProvinceNotExist(id);
        return provinceRepository.getOne(id);
    }

    public void delete(Integer id) {
        verifyIfProvinceNotExist(id);
        provinceRepository.deleteById(id);
    }

    private void verifyIfProvinceNotExist(Integer id) {
        Optional<Province> foundProvince = provinceRepository.findById(id);
        if (!foundProvince.isPresent()) {
            throw new ProvinceNotFoundException();
        }
    }

    private void verifyIfProvinceExists(Province province) {
        Optional<Province> foundProvince = provinceRepository.findByProvinceName(province.getProvinceName());
        if (foundProvince.isPresent() && (province.isNew() || isUpadatingToADifferentProvince(province, foundProvince))) {
            throw new ProvinceExistException();
        }
    }

    private boolean isUpadatingToADifferentProvince(Province province, Optional<Province> foundProvince) {
        return province.exist() && !(province.equals(foundProvince.get()));
    }
}
