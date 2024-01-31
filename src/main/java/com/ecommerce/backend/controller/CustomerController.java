package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.CustomerModel;
import com.ecommerce.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<CustomerModel> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerModel getCustomerById(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId).orElse(null);
    }

    @PostMapping
    public CustomerModel createCustomer(@RequestBody CustomerModel customerModel){
        return customerService.createCustomer(customerModel);
    }

    @PutMapping("/{customerId}")
    public CustomerModel updateCustomer(@PathVariable Long customerId, @RequestBody CustomerModel updateCustomer){
        return customerService.updateCustomer(customerId, updateCustomer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
    }
}
