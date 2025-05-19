package org.exp.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Product;
import org.exp.ecommerce.api.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

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

    public Page<Product> getAllByCategoryIdPageable(Integer categoryId, Pageable pageable) {
        return productRepository.findAllByCategory_Id(categoryId, pageable);
    }

    public Object update(Integer id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setCount(updatedProduct.getCount());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    existingProduct.setAttachment(updatedProduct.getAttachment());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id = " + id));
    }

    public Object activate(Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.set_active(true); // yoki false qilish ham mumkin
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id = " + id));
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public Object deactivate(Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.set_active(false);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id = " + id));
    }
}
