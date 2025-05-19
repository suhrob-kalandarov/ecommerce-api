package org.exp.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exp.ecommerce.api.models.commerce.Order;
import org.exp.ecommerce.api.models.commerce.OrderedItem;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private boolean active;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private Double subtotal;
    private Double shipping;
    private Double tax;
    private Double total;
    private String status;
    private List<OrderedItemDTO> orderedItems;

    public static OrderDTO fromOrder(Order order, List<OrderedItem> orderedItems) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setActive(order.is_active());

        // Map all fields from Order entity
        dto.setCustomerName(order.getCustomerName());
        dto.setCustomerEmail(order.getCustomerEmail());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setSubtotal(order.getSubtotal());
        dto.setShipping(order.getShipping());
        dto.setTax(order.getTax());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus());

        // Convert OrderedItems to DTOs
        if (orderedItems != null) {
            dto.setOrderedItems(orderedItems.stream()
                    .map(OrderedItemDTO::fromOrderedItem)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}