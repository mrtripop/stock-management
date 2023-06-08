package com.learn.service.Customer;

import com.learn.model.Customer.CustomerModel;
import com.learn.repository.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> getAllCustomer() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public CustomerModel insertCustomer(CustomerModel customer) {
        try {
            customerRepository.save(customer);
            return customer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean deleteCustomer(Integer id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public CustomerModel updateCustomerName(Integer id, CustomerModel newCustomerInfo) {
//        try {
//            Optional<CustomerModel> customerInfo = customerRepository.findById(id);
//            customerInfo.map(customer -> {
//                customer.setName(newCustomerInfo.getName());
//                customerRepository.save(customer);
//                return customer;
//            }).orElseGet(() -> customerRepository.save(newCustomerInfo));
//            return customerInfo.get();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
        return null;
    }
}
