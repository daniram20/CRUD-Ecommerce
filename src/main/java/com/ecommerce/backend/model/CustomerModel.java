package com.ecommerce.backend.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name="customers")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerAddress;
    private String customerCode;
    private String customerName;
    private String customerPhone;
    private Boolean isActive;
    private LocalDate lastOrderDate;
    private String pic;

    @OneToMany(mappedBy = "customerModel")
    private Set<OrderModel> orders;
}