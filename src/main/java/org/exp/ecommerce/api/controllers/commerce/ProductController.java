package org.exp.ecommerce.api.controllers.commerce;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public HttpEntity<?> getAll() {
        return (HttpEntity<?>) productService.getAll();
    }


}
