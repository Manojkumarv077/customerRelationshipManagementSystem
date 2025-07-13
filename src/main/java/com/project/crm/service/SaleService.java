package com.project.crm.service;

import com.project.crm.dto.SaleDTO;
import com.project.crm.model.Sale;

import java.util.List;

public interface SaleService {
    Sale createSale(SaleDTO saleDTO);
    List<Sale> getSalesByCustomer(Long customerId);
}
