package org.exp.ecommerce.api.models.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.exp.ecommerce.api.models.base.BaseEntity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered_items")
public class OrderedItem extends BaseEntity {

    private Integer quantity;
    private Double price;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore // This breaks the circular reference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}