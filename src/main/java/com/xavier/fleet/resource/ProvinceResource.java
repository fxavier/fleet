package com.xavier.fleet.resource;


import com.xavier.fleet.model.Province;
import com.xavier.fleet.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
public class ProvinceResource {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public List<Province> findAll() {
        return provinceService.findAll();
    }

    @GetMapping("/{id}")
    public Province findById(@PathVariable Integer id) {
        return provinceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Province create(@Valid @RequestBody Province province) {
        return provinceService.save(province);
    }

    @PutMapping("/{id}")
    public Province update(@PathVariable Integer id, @Valid @RequestBody Province province) {
        province.setProvinceId(id);
        return provinceService.save(province);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id) {
        provinceService.delete(id);
    }
}
