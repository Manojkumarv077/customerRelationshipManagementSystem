package com.project.crm.service.impl;

import com.project.crm.dto.ReportDTO;
import com.project.crm.repository.InteractionLogRepository;
import com.project.crm.repository.SaleRepository;
import com.project.crm.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private InteractionLogRepository interactionLogRepository;

    @Override
    public List<ReportDTO> getSalesByMonth() {
        return saleRepository.getTotalSalesByMonth();
    }

    @Override
    public List<ReportDTO> getSalesPerCustomer() {
        return saleRepository.getTotalSalesPerCustomer();
    }

    @Override
    public List<ReportDTO> getMostActiveCustomers() {
        return interactionLogRepository.getMostActiveCustomers();
    }
}
