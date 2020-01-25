package com.xavier.fleet.resource;

import com.xavier.fleet.model.City;
import com.xavier.fleet.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityResource {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> search(@RequestParam Integer provinceId) {
        return cityService.findByProvince(provinceId);
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Integer id) {
        return cityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@Valid @RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Integer id, @Valid @RequestBody City city) {
        city.setCityId(id);
        return cityService.save(city);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id) {
        cityService.delete(id);
    }
}
