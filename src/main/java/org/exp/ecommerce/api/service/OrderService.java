package org.exp.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Order;
import org.exp.ecommerce.api.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Integer id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderedItems(updatedOrder.getOrderedItems());
            return orderRepository.save(order);
        }).orElse(null);
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}