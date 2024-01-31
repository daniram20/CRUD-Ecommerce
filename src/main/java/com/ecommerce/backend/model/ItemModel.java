package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "items")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private Boolean isAvailable;
    private String itemCode;
    private String itemName;
    private LocalDate lastReStock;
    private BigDecimal price;
    private Integer stock;

    @OneToMany(mappedBy = "itemModel")
    private Set<OrderModel> orders;
}
