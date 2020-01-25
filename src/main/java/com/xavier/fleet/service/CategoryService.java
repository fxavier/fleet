package com.xavier.fleet.service;

import com.xavier.fleet.model.Category;
import com.xavier.fleet.repository.CategoryRepository;
import com.xavier.fleet.service.exception.CategoryExistException;
import com.xavier.fleet.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(@Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        verifyIfCategoryNotExist(id);
        return categoryRepository.getOne(id);
    }

    public Category save(final Category category) {
        verifyIfCategoryExist(category);
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        verifyIfCategoryNotExist(id);
        categoryRepository.deleteById(id);
    }


    private void verifyIfCategoryExist(Category category) throws CategoryExistException {
        Optional<Category> foundCategory = categoryRepository.findByName(category.getName());
        if (foundCategory.isPresent() && (category.isNew() || isUpdatingToADifferentEntity(category, foundCategory))) {
            throw new CategoryExistException();
        }
    }

    private void verifyIfCategoryNotExist(Integer id) throws CategoryNotFoundException {
        Optional<Category> foundPartCategory = categoryRepository.findById(id);
        if (!foundPartCategory.isPresent()) {
            throw new CategoryNotFoundException();
        }
    }

    private boolean isUpdatingToADifferentEntity(Category category, Optional<Category> foundCategory) {
        return category.exist() && !category.equals(foundCategory);
    }
}
