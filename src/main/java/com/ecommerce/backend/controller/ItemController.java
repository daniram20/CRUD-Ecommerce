package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.ItemModel;
import com.ecommerce.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public List<ItemModel> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public ItemModel getItemById(@PathVariable Long itemId){
        return itemService.getItemById(itemId).orElse(null);
    }

    @PostMapping
    public ItemModel createItem(@RequestBody ItemModel itemModel){
        return itemService.createItem(itemModel);
    }

    @PutMapping("/{itemId}")
    public ItemModel updateItem(@PathVariable Long itemId, @RequestBody ItemModel updateItem){
        return itemService.updateItem(itemId, updateItem);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
    }
}
