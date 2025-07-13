package com.project.crm.repository;

import com.project.crm.dto.ReportDTO;
import com.project.crm.model.InteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InteractionLogRepository extends JpaRepository<InteractionLog, Long> {
    List<InteractionLog> findByCustomerId(Long customerId);
    @Query("SELECT new com.project.crm.dto.ReportDTO(c.name, COUNT(i)) " +
    	       "FROM InteractionLog i JOIN i.customer c GROUP BY c.id, c.name ORDER BY COUNT(i) DESC")
    	List<ReportDTO> getMostActiveCustomers();

}
