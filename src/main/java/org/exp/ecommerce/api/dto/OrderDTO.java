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
        // Set other fields from your Order entity

        // Convert OrderedItems to DTOs
        if (orderedItems != null) {
            dto.setOrderedItems(orderedItems.stream()
                    .map(OrderedItemDTO::fromOrderedItem)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderedItemDTO {
    private Integer id;
    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
    private String imageUrl;

    public static OrderedItemDTO fromOrderedItem(OrderedItem item) {
        OrderedItemDTO dto = new OrderedItemDTO();
        dto.setId(item.getId());

        if (item.getProduct() != null) {
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            // Set image URL if available
            if (item.getProduct().getAttachment() != null) {
                dto.setImageUrl("/api/attachments/" + item.getProduct().getAttachment().getId());
            }
        }

        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setTotalPrice(item.getTotalPrice());

        return dto;
    }
}