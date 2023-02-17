package com.learn.service;

import com.learn.model.postgres.Customer;
import com.learn.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setup() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
        customer = Customer.builder()
                .id(1)
                .name("Alex")
                .email("alex@gmail.com")
                .age(24)
                .build();
    }

    @Test
    @DisplayName("Get all customer with empty customer")
    void getAllCustomerWithEmptyCustomer() {
        given(customerRepository.findAll()).willReturn(Collections.emptyList());
        // when -  action or the behaviour that we are going test
        List<Customer> customerList = customerService.getAllCustomer();
        // then - verify the output
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Get all customer")
    void getAllCustomer() {
        given(customerRepository.findAll()).willReturn(List.of(customer));
        // when -  action or the behaviour that we are going test
        List<Customer> customerList = customerService.getAllCustomer();
        // then - verify the output
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Create new customer")
    void insertCustomer() {
        // precondition
        given(customerRepository.save(customer)).willReturn(customer);
        // when
        Customer createNewCustomer = customerRepository.save(customer);
        // then
        assertThat(createNewCustomer).isNotNull();
        assertThat(createNewCustomer).isEqualTo(customer);
    }

    @Test
    void deleteCustomer() {
        // pre-condition
        Integer id = 1;
        willDoNothing().given(customerRepository).deleteById(id);
        // when
        customerRepository.deleteById(id);
        // then
        verify(customerRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Update customer name")
    void updateCustomerName() {
        // pre-condition
        given(customerRepository.save(customer)).willReturn(customer);
        // when
        customer.setName("David");
        Customer updateCustomer = customerRepository.save(customer);
        // then
        assertThat(updateCustomer).isEqualTo(customer);
    }
}