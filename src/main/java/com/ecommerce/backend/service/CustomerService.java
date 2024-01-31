package com.ecommerce.backend.service;

import com.ecommerce.backend.model.CustomerModel;
import com.ecommerce.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //CREATE
    public CustomerModel createCustomer(CustomerModel customerModel){
        customerModel.setLastOrderDate(LocalDate.now());
        return customerRepository.save(customerModel);
    }

    //READ
    public List<CustomerModel> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> getCustomerById(Long customerId){
        return customerRepository.findById(customerId);
    }

    //Update
    public CustomerModel updateCustomer(Long customerId, CustomerModel updateCustomer){
        Optional<CustomerModel> existingCustomerOptional = customerRepository.findById(customerId);

        if (existingCustomerOptional.isPresent()){
            CustomerModel existingCustomer = existingCustomerOptional.get();
            existingCustomer.setCustomerName(updateCustomer.getCustomerName());
            existingCustomer.setCustomerAddress(updateCustomer.getCustomerAddress());
            existingCustomer.setCustomerCode(updateCustomer.getCustomerCode());
            existingCustomer.setCustomerPhone(updateCustomer.getCustomerPhone());
            existingCustomer.setIsActive(updateCustomer.getIsActive());
            return customerRepository.save(existingCustomer);
        } else {
            return null;
        }
    }
    //DELETE
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }
}
