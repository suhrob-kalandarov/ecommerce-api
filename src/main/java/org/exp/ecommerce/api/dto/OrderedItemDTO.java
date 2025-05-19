package org.exp.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exp.ecommerce.api.models.commerce.OrderedItem;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItemDTO {
    private Integer id;
    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
    private String imageUrl;

    public static OrderedItemDTO fromOrderedItem(OrderedItem item) {
        OrderedItemDTO dto = new OrderedItemDTO();

        if (item == null) {
            return dto;
        }

        dto.setId(item.getId());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setTotalPrice(item.getTotalPrice());

        if (item.getProduct() != null) {
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());

            // Set image URL if available
            if (item.getProduct().getAttachment() != null) {
                dto.setImageUrl("/api/attachments/" + item.getProduct().getAttachment().getId());
            }
        }

        return dto;
    }
}