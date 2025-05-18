package org.exp.ecommerce.api.repositories;

import org.exp.ecommerce.api.models.commerce.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
