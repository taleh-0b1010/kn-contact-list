package com.kn.controller;

import com.kn.model.ContactDTO;
import com.kn.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService service;

    @Test
    void getContacts() throws Exception {
        ContactDTO contactDTO = ContactDTO.builder()
                .firstName("John")
                .lastName("Wick").build();
        List<ContactDTO> contactDTOs = List.of(contactDTO);
        given(service.getContacts(1, 5)).willReturn(contactDTOs);
        mockMvc.perform(get("/contacts")
                .param("pageNo", String.valueOf(1))
                .param("pageSize", String.valueOf(5)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].firstName", is("John")))
                .andExpect(jsonPath("$.[0].lastName", is("Wick")));
    }

    @Test
    void getContactsByFirstName() throws Exception {
        ContactDTO contactDTO = ContactDTO.builder()
                .firstName("John")
                .lastName("Wick").build();
        List<ContactDTO> contactDTOs = List.of(contactDTO);
        given(service.getContactsByFirstName("John")).willReturn(contactDTOs);
        mockMvc.perform(get("/contacts/search-by-name")
                        .param("firstName", "John"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].firstName", is("John")))
                .andExpect(jsonPath("$.[0].lastName", is("Wick")));
    }
}