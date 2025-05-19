package org.exp.ecommerce.api.controllers.commerce;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.dto.OrderDTO;
import org.exp.ecommerce.api.models.commerce.Order;
import org.exp.ecommerce.api.models.commerce.OrderedItem;
import org.exp.ecommerce.api.service.OrderItemService;
import org.exp.ecommerce.api.service.OrderService;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Order order = orderService.getById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        // Get ordered items for this order
        List<OrderedItem> orderedItems = orderItemService.getByOrderId(id);

        // Convert to DTO to avoid circular reference
        OrderDTO orderDTO = OrderDTO.fromOrder(order, orderedItems);

        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Order> orders = orderService.getAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> {
                    List<OrderedItem> items = orderItemService.getByOrderId(order.getId());
                    return OrderDTO.fromOrder(order, items);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDTOs);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.create(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order order) {
        Order updated = orderService.update(id, order);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}