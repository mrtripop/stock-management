package com.learn.controller.Inventory;

import com.learn.model.Customer.CustomerModel;
import com.learn.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    private final CustomerService customerService;

    @Autowired
    public InventoryController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerModel> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping
    public CustomerModel insertNewCustomer(@RequestBody CustomerModel customer) {
        return customerService.insertCustomer(customer);
    }

    @DeleteMapping("{customerId}")
    public boolean deleteCustomer(@PathVariable("customerId") Integer customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @PutMapping("{customerId}")
    public CustomerModel updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody CustomerModel customer) {
        return customerService.updateCustomerName(customerId, customer);
    }
}
