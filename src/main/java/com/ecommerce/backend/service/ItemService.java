package com.ecommerce.backend.service;

import com.ecommerce.backend.model.CustomerModel;
import com.ecommerce.backend.model.ItemModel;
import com.ecommerce.backend.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    //CREATE
    public ItemModel createItem(ItemModel itemModel){
        itemModel.setLastReStock(LocalDate.now());
        return itemRepository.save(itemModel);
    }

    //READ
    public List<ItemModel> getAllItems(){
        return itemRepository.findAll();
    }

    public Optional<ItemModel> getItemById(Long itemId){
        return itemRepository.findById(itemId);
    }

    //UPDATE
    public ItemModel updateItem(Long itemId, ItemModel updateItem){
        Optional<ItemModel> existingItemOptional = itemRepository.findById(itemId);

        if(existingItemOptional.isPresent()){
            ItemModel existingItem = existingItemOptional.get();
            existingItem.setIsAvailable(updateItem.getIsAvailable());
            existingItem.setItemCode(updateItem.getItemCode());
            existingItem.setItemName(updateItem.getItemName());
            existingItem.setPrice(updateItem.getPrice());
            existingItem.setStock(updateItem.getStock());
            logger.info("Item updated: {}", updateItem);
            return itemRepository.save(existingItem);
        } else {
            logger.warn("Item not found with ID: {}", itemId);
            return null;
        }
    }
    //DELETE
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
        logger.info("Item deleted with Id: {}", itemId);
    }
}
