package com.project.crm.service;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerDTO dto);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, CustomerDTO dto);
    void deleteCustomer(Long id);
}
