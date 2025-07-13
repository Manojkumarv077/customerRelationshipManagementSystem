package com.project.crm.controller;

import com.project.crm.dto.SaleDTO;
import com.project.crm.model.Sale;
import com.project.crm.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale create(@RequestBody SaleDTO dto) {
        return saleService.createSale(dto);
    }

    @GetMapping("/customer/{customerId}")
    public List<Sale> getByCustomer(@PathVariable Long customerId) {
        return saleService.getSalesByCustomer(customerId);
    }
}
