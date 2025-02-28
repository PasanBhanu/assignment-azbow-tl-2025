package com.pasan.samples.leadcrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasan.samples.leadcrm.controller.model.CreateLeadRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class LeadControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void createLeadTest() throws Exception {
        CreateLeadRequest createLeadRequest = new CreateLeadRequest("test", "0715454904", "call", Date.valueOf("2025-02-01"));
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/lead")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createLeadRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("statusCode", is("201")));
    }

    @Test
    public void createLeadTest_Invalid() throws Exception {
        CreateLeadRequest createLeadRequest = new CreateLeadRequest("test", "0715454904", "call", Date.valueOf("2025-12-01"));
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/lead")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createLeadRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("statusCode", is("400")));
    }

    @Test
    public void getLeads() throws Exception {
        CreateLeadRequest createLeadRequest = new CreateLeadRequest("test", "0715454904", "call", Date.valueOf("2025-02-01"));
        ObjectMapper mapper = new ObjectMapper();

        // Insert Record
        MvcResult mvcResult = mockMvc.perform(post("/lead")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createLeadRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").exists())
                .andReturn();

        int leadId = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString()).get("data").get("id").asInt();

        // Test Get 1
        mockMvc.perform(get("/lead/{id}", leadId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name").value("test"));

        // Test Get All
        mockMvc.perform(get("/lead")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.leads", hasSize(greaterThanOrEqualTo(1))));
    }
}
