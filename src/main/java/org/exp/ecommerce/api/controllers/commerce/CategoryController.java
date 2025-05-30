package org.exp.ecommerce.api.controllers.commerce;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Category;
import org.exp.ecommerce.api.request.CategoryReq;
import org.exp.ecommerce.api.service.CategoryService;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // GET ALL - Accessible to all authenticated users
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Category> all = categoryService.getAll();
        return ResponseEntity.ok(all);
    }

    // GET BY ID - Accessible to all authenticated users
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.findById(id);
        return category
                .<HttpEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404
    }

    // CREATE - Only accessible to ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> create(@RequestBody Category category) {
        category.setId(null);
        Category saved = categoryService.saveReturn(category);
        return ResponseEntity.ok(saved);
    }

    // FULL UPDATE - Only accessible to ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody CategoryReq updatedCategory) {
        Optional<Category> optional = categoryService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        Category category = optional.get();
        category.setName(updatedCategory.getName());
        Category saved = categoryService.saveReturn(category);
        return ResponseEntity.ok(saved);
    }

    // PARTIAL UPDATE - Only accessible to ADMIN
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> partialUpdate(@PathVariable Integer id, @RequestBody CategoryReq updatedCategory) {
        Optional<Category> optional = categoryService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        Category category = optional.get();
        if (updatedCategory.getName() != null)
            category.setName(updatedCategory.getName());

        Category saved = categoryService.saveReturn(category);
        return ResponseEntity.ok(saved);
    }

    // DELETE - Only accessible to ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        if (!categoryService.existsById(id))
            return ResponseEntity.notFound().build();

        categoryService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
