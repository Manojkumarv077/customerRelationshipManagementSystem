package com.project.crm.repository;

import com.project.crm.dto.ReportDTO;
import com.project.crm.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query("SELECT new com.project.crm.dto.ReportDTO(FUNCTION('DATE_FORMAT', s.date, '%Y-%m'), SUM(s.amount)) " +
		       "FROM Sale s GROUP BY FUNCTION('DATE_FORMAT', s.date, '%Y-%m') ORDER BY FUNCTION('DATE_FORMAT', s.date, '%Y-%m')")
		List<ReportDTO> getTotalSalesByMonth();

	@Query("SELECT new com.project.crm.dto.ReportDTO(c.name, SUM(s.amount)) " +
		       "FROM Sale s JOIN s.customer c GROUP BY c.id, c.name ORDER BY SUM(s.amount) DESC")
		List<ReportDTO> getTotalSalesPerCustomer();

    List<Sale> findByCustomerId(Long customerId);
}
