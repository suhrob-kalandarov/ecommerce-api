package org.exp.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Category;
import org.exp.ecommerce.api.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category saveReturn(Category category){
        return categoryRepository.save(category);
    }

    public boolean existsById(Integer id) {
        return categoryRepository.existsById(id);
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
