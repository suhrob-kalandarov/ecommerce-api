package org.exp.ecommerce.api.controllers.commerce;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Order;
import org.exp.ecommerce.api.models.commerce.OrderedItem;
import org.exp.ecommerce.api.service.OrderItemService;
import org.exp.ecommerce.api.service.OrderService;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/ordered-items")
public class OrderedItemController {

    private final OrderItemService service;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        OrderedItem item = service.getById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<?> getByOrderId(@PathVariable Integer orderId) {
        List<OrderedItem> items = service.getByOrderId(orderId);
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderedItem item) {
        if (item.getOrder() != null && item.getOrder().getId() != null) {
            Order order = orderService.getById(item.getOrder().getId());
            if (order != null) {
                item.setOrder(order);
                order.getOrderedItems().add(item);
                OrderedItem savedItem = service.create(item);
                return ResponseEntity.ok(savedItem);
            }
        }
        return ResponseEntity.badRequest().body("Invalid order reference");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody OrderedItem item) {
        OrderedItem updated = service.update(id, item);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
