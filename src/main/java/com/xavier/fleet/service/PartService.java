package com.xavier.fleet.service;

import com.xavier.fleet.model.Part;
import com.xavier.fleet.repository.PartRepository;
import com.xavier.fleet.service.exception.PartExistException;
import com.xavier.fleet.service.exception.PartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public PartService(@Autowired PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Part findById(Long id) {
        verifyIfPartNotExist(id);
        return partRepository.getOne(id);
    }

    public Part save(final Part part) {
        verifyIfPartExist(part);
        return partRepository.save(part);
    }

    public void delete(Long id) {
        verifyIfPartNotExist(id);
        partRepository.deleteById(id);
    }

    private void verifyIfPartExist(Part part) throws PartExistException {
        Optional<Part> foundPart = partRepository.findByName(part.getName());
        if (foundPart.isPresent() && (part.isNew() || isUpdatingToADifferentEntity(part, foundPart))) {
            throw new PartExistException();
        }
    }

    private boolean isUpdatingToADifferentEntity(Part part, Optional<Part> foundPart) {
        return part.exist() && !part.equals(foundPart.get());
    }

    private void verifyIfPartNotExist(Long id) throws PartNotFoundException {
        Optional<Part> foundPart = partRepository.findById(id);
        if (!foundPart.isPresent()) {
            throw new PartNotFoundException();
        }
    }

}
