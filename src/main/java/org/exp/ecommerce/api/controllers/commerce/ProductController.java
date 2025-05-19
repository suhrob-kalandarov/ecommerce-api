package org.exp.ecommerce.api.controllers.commerce;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Product;
import org.exp.ecommerce.api.service.ProductService;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/products")
public class ProductController {

    private final ProductService productService;

    // GET: barcha productlar
    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    // GET: product by ID
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // GET: category ID bo‘yicha barcha productlar
    @GetMapping("/by-category/{categoryId}")
    public HttpEntity<?> getByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(productService.getAllByCategoryId(categoryId));
    }

    // GET: category ID bo‘yicha paginate qilingan productlar
    @GetMapping("/by-category/{categoryId}/page")
    public HttpEntity<?> getByCategoryPageable(@PathVariable Integer categoryId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getAllByCategoryIdPageable(categoryId, PageRequest.of(page, size));
        return ResponseEntity.ok(products);
    }


    /// /// /// /// /// /// /// ///


    // POST: yangi product qo‘shish
    @PostMapping
    public HttpEntity<?> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    // PUT: to‘liq yangilash
    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    // PATCH: faqat aktivligini o‘zgartirish (namuna)
    @PatchMapping("/{id}/activate")
    public HttpEntity<?> activate(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.activate(id));
    }

    // DELETE: mahsulotni o‘chirish
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}

