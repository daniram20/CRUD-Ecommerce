package com.ecommerce.backend.model;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderCode;
    private Date orderDate;
    private Integer quantity;
    private BigDecimal totalPrice;

    private Long customerId;
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private CustomerModel customerModel;

    @ManyToOne
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
    private ItemModel itemModel;
}
