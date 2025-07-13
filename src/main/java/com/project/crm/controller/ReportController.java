package com.project.crm.controller;

import com.project.crm.dto.ReportDTO;
import com.project.crm.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/sales-by-month")
    public List<ReportDTO> salesByMonth() {
        return reportService.getSalesByMonth();
    }

    @GetMapping("/sales-per-customer")
    public List<ReportDTO> salesPerCustomer() {
        return reportService.getSalesPerCustomer();
    }

    @GetMapping("/most-active-customers")
    public List<ReportDTO> mostActiveCustomers() {
        return reportService.getMostActiveCustomers();
    }
    @GetMapping("/sales/monthly/csv")
    public void exportMonthlySalesCSV(HttpServletResponse response) throws IOException {
        List<ReportDTO> report = reportService.getSalesByMonth();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"sales_monthly_report.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("Month,Total Sales");

        for (ReportDTO dto : report) {
            writer.println(dto.getLabel() + "," + dto.getTotal());
        }

        writer.flush();
        writer.close();
    }

}
