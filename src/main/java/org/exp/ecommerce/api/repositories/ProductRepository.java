package org.exp.ecommerce.api.repositories;

import org.exp.ecommerce.api.models.commerce.Category;
import org.exp.ecommerce.api.models.commerce.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategory_Id(Integer categoryId);

    Product findByCategory_Id(Integer id);

    List<Product> findByCategory(Category category);

    Page<Product> findAllByCategory_Id(Integer categoryId, Pageable pageable);

}
