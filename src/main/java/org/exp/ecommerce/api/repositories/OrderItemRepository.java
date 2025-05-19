package org.exp.ecommerce.api.repositories;

import org.exp.ecommerce.api.models.commerce.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderedItem, Integer> {

    List<OrderedItem> findAllByOrder_Id(Integer orderId);
}
