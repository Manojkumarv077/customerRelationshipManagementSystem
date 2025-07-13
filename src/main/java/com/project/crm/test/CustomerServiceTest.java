package com.project.crm.test;

import com.project.crm.model.Customer;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testGetAllCustomers() {
        Customer c = new Customer();
        c.setName("Alice");
        c.setEmail("alice@example.com");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(c));

        List<Customer> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("alice@example.com", result.get(0).getEmail());
    }
}
