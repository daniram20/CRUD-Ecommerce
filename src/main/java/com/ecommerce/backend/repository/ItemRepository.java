package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
}
