package com.project.crm.controller;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.model.Customer;
import com.project.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer create(@RequestBody CustomerDTO dto) {
        return customerService.createCustomer(dto);
    }

    @GetMapping
    public List<Customer> all() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
    @GetMapping("/api/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}
