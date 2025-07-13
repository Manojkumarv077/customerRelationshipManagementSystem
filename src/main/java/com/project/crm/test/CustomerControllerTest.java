package com.project.crm.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.crm.controller.CustomerController;
import com.project.crm.model.Customer;
import com.project.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L); // if id is required
        customer.setName("Alice");
        customer.setEmail("alice@example.com");
        customer.setPhone("1234567890");

        Mockito.when(customerService.getAllCustomers())
               .thenReturn(Collections.singletonList(customer));

        mockMvc.perform(get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
                .andExpect(jsonPath("$[0].phone").value("1234567890"));
    }
}
