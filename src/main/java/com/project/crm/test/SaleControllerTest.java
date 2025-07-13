package com.project.crm.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.crm.controller.SaleController;
import com.project.crm.dto.SaleDTO;
import com.project.crm.model.Customer;
import com.project.crm.model.Sale;
import com.project.crm.service.SaleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaleController.class)
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService saleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateSale() throws Exception {
        SaleDTO dto = new SaleDTO();
        dto.setProductName("Router");
        dto.setAmount(2500.0);
        dto.setDate(LocalDate.now());
        dto.setCustomerId(1L);

        Sale sale = new Sale();
        sale.setId(1L);
        sale.setAmount(2500.0);
        sale.setProductName("Router");
        sale.setDate(LocalDate.now());
        sale.setCustomer(new Customer());

        Mockito.when(saleService.createSale(any(SaleDTO.class))).thenReturn(sale);

        mockMvc.perform(post("/api/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(2500.0));
    }

    @Test
    void testGetSalesByCustomer() throws Exception {
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setProductName("Keyboard");
        sale.setAmount(800.0);

        Mockito.when(saleService.getSalesByCustomer(1L))
                .thenReturn(Collections.singletonList(sale));

        mockMvc.perform(get("/api/sales/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value("Keyboard"));
    }
}
