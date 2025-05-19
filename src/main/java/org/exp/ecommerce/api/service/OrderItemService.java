package org.exp.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.commerce.OrderedItem;
import org.exp.ecommerce.api.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository repository;

    public List<OrderedItem> getAll() {
        return repository.findAll();
    }

    public OrderedItem getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<OrderedItem> getByOrderId(Integer orderId) {
        return repository.findAllByOrder_Id(orderId);
    }

    public OrderedItem create(OrderedItem item) {
        item.setTotalPrice(item.getQuantity() * item.getPrice());
        return repository.save(item);
    }

    public OrderedItem update(Integer id, OrderedItem updatedItem) {
        return repository.findById(id).map(item -> {
            item.setQuantity(updatedItem.getQuantity());
            item.setPrice(updatedItem.getPrice());
            item.setTotalPrice(updatedItem.getQuantity() * updatedItem.getPrice());
            item.setProduct(updatedItem.getProduct());
            item.setOrder(updatedItem.getOrder());
            return repository.save(item);
        }).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
