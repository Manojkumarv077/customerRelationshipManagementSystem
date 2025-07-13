package com.project.crm.test;

import com.project.crm.dto.SaleDTO;
import com.project.crm.model.Customer;
import com.project.crm.model.Sale;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.repository.SaleRepository;
import com.project.crm.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSale_successful() {
        SaleDTO dto = new SaleDTO();
        dto.setAmount(1500.0);
        dto.setProductName("Tablet");
        dto.setDate(LocalDate.now());
        dto.setCustomerId(1L);

        Customer customer = new Customer();
        customer.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Sale saved = new Sale();
        saved.setId(1L);
        saved.setAmount(1500.0);
        saved.setCustomer(customer);

        when(saleRepository.save(any(Sale.class))).thenReturn(saved);

        Sale result = saleService.createSale(dto);

        assertNotNull(result);
        assertEquals(1500.0, result.getAmount());
        verify(saleRepository, times(1)).save(any(Sale.class));
    }

    @Test
    void createSale_customerNotFound() {
        SaleDTO dto = new SaleDTO();
        dto.setCustomerId(99L);

        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> {
            saleService.createSale(dto);
        });

        assertTrue(ex.getMessage().contains("Customer not found"));
        verify(saleRepository, never()).save(any());
    }
}
