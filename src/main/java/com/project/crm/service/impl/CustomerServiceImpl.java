package com.project.crm.service.impl;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.model.Customer;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public Customer createCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return repo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = getCustomerById(id);
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return repo.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }
}
