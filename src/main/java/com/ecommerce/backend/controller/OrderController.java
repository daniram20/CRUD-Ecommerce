package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.OrderModel;
import com.ecommerce.backend.service.OrderService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderModel> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderModel getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId).orElse(null);
    }

    @PostMapping
    public OrderModel createOrder(@RequestBody OrderModel orderModel){
        return orderService.createOrder(orderModel);
    }

    @PutMapping("/{orderId}")
    public OrderModel updateOrder(@PathVariable Long orderId, @RequestBody OrderModel updateOrder){
        return orderService.updateOrder(orderId, updateOrder);
    }

    @DeleteMapping("/{orederId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }


    @GetMapping("/report/{format}")
    public String generatedReport(@PathVariable String format) throws FileNotFoundException, JRException{
        return orderService.exportReport(format);
    }

}
