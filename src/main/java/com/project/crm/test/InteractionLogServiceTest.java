package com.project.crm.test;

import com.project.crm.dto.InteractionLogDTO;
import com.project.crm.exception.ResourceNotFoundException;
import com.project.crm.model.Customer;
import com.project.crm.model.InteractionLog;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.repository.InteractionLogRepository;
import com.project.crm.service.impl.InteractionLogServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InteractionLogServiceTest {

    @InjectMocks
    private InteractionLogServiceImpl interactionLogService;

    @Mock
    private InteractionLogRepository interactionLogRepository;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createInteraction_success() {
        InteractionLogDTO dto = new InteractionLogDTO();
        dto.setType("EMAIL");
        dto.setNotes("Follow-up mail sent");
        dto.setDate(LocalDate.now());
        dto.setCustomerId(1L);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        InteractionLog saved = new InteractionLog();
        saved.setId(1L);
        saved.setCustomer(customer);

        when(interactionLogRepository.save(any(InteractionLog.class))).thenReturn(saved);

        InteractionLog result = interactionLogService.createInteraction(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(interactionLogRepository, times(1)).save(any(InteractionLog.class));
    }

    @Test
    void createInteraction_customerNotFound() {
        InteractionLogDTO dto = new InteractionLogDTO();
        dto.setCustomerId(999L);

        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            interactionLogService.createInteraction(dto);
        });

        verify(interactionLogRepository, never()).save(any());
    }

    @Test
    void getInteractionsByCustomer_success() {
        Long customerId = 1L;

        InteractionLog log1 = new InteractionLog();
        log1.setId(1L);
        log1.setType("CALL");

        InteractionLog log2 = new InteractionLog();
        log2.setId(2L);
        log2.setType("EMAIL");

        when(interactionLogRepository.findByCustomerId(customerId))
                .thenReturn(Arrays.asList(log1, log2));

        List<InteractionLog> result = interactionLogService.getInteractionsByCustomer(customerId);

        assertEquals(2, result.size());
        assertEquals("CALL", result.get(0).getType());
        assertEquals("EMAIL", result.get(1).getType());
    }
}
