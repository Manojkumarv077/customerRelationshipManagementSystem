package com.project.crm.service;

import com.project.crm.dto.ReportDTO;
import java.util.List;

public interface ReportService {
    List<ReportDTO> getSalesByMonth();
    List<ReportDTO> getSalesPerCustomer();
    List<ReportDTO> getMostActiveCustomers();
}
