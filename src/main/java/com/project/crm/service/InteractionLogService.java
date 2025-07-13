package com.project.crm.service;

import com.project.crm.dto.InteractionLogDTO;
import com.project.crm.model.InteractionLog;

import java.util.List;

public interface InteractionLogService {
    InteractionLog createInteraction(InteractionLogDTO dto);
    List<InteractionLog> getInteractionsByCustomer(Long customerId);
}
