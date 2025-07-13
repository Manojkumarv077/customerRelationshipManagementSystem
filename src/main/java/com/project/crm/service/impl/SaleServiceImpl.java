package com.project.crm.service.impl;

import com.project.crm.dto.SaleDTO;
import com.project.crm.exception.ResourceNotFoundException;
import com.project.crm.model.Customer;
import com.project.crm.model.Sale;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.repository.SaleRepository;
import com.project.crm.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SaleServiceImpl implements SaleService {

    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public Sale createSale(SaleDTO dto) {
        logger.info("Attempting to create sale for customer ID: {}", dto.getCustomerId());

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> {
                    logger.warn("Customer not found with ID: {}", dto.getCustomerId());
                    return new ResourceNotFoundException("Customer not found");
                });

        Sale sale = new Sale();
        sale.setProductName(dto.getProductName());
        sale.setAmount(dto.getAmount());
        sale.setDate(dto.getDate());
        sale.setCustomer(customer);

        Sale savedSale = saleRepo.save(sale);
        logger.info("Sale created successfully. Sale ID: {}, Amount: {}", savedSale.getId(), savedSale.getAmount());

        return savedSale;
    }

    @Override
    public List<Sale> getSalesByCustomer(Long customerId) {
        logger.info("Fetching sales for customer ID: {}", customerId);
        List<Sale> sales = saleRepo.findByCustomerId(customerId);
        logger.info("Found {} sale(s) for customer ID: {}", sales.size(), customerId);
        return sales;
    }
}
