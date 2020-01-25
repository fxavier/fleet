package com.xavier.fleet.resource;

import com.xavier.fleet.model.Part;
import com.xavier.fleet.repository.PartRepository;
import com.xavier.fleet.repository.filter.PartFilter;
import com.xavier.fleet.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parts")
public class PartResource {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    @GetMapping
    public Page<Part> search(PartFilter partFilter, Pageable pageable) {
        return partRepository.filter(partFilter, pageable);
    }

    @GetMapping("/{id}")
    public Part findById(@PathVariable Long id) {
        return partService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Part create(@Valid @RequestBody Part part) {
        return partService.save(part);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Part update(@PathVariable Long id, @Valid @RequestBody Part part) {
        part.setId(id);
        return partService.save(part);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        partService.delete(id);
    }
}
