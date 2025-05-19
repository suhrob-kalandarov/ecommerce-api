package org.exp.ecommerce.api.models.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.exp.ecommerce.api.models.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private Double subtotal;
    private Double shipping;
    private Double tax;
    private Double total;
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Add this to break the circular reference
    private List<OrderedItem> orderedItems = new ArrayList<>();
}