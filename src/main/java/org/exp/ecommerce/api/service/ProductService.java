package org.exp.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Product;
import org.exp.ecommerce.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    public Optional<Product> getByIdOptional(Integer id) {
        return productRepository.findById(id);
    }

    public Product getByCategoryId(Integer id) {
        return productRepository.findByCategory_Id(id);
    }

    public List<Product> getAllByCategoryId(Integer id) {
        return productRepository.findAllByCategory_Id(id);
    }

    public List<Product> getByAllCategoryIdPagaAble(Integer id) {
        return productRepository.findAllByCategory_Id(id);
    }
}
