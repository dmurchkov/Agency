package com.dmurchkov.service.agency.rest;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.model.Ad;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgencyService agencyService;

    @Test
    public void shouldReturnAllAds() throws Exception {
        when(agencyService.getAll()).thenReturn(Collections.emptyMap());
        this.mockMvc.perform(get("/agency/ads/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void shoudReturnAdById() throws Exception {
        when(agencyService.getById(10)).thenReturn(new Ad());
        this.mockMvc.perform(get("/agency/ads/10"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldThrowNoSuchAdException() throws Exception {
        when(agencyService.getById(10)).thenReturn(null);
        this.mockMvc.perform(get("/agency/ads/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateNewAd() throws Exception {
        when(agencyService
                .submit(anyString(), anyString(), anyString(), anyDouble(), anyInt(), anyLong(), anyInt(), anyString(),
                        anyString(), anyString(), anyObject())).thenReturn(1000L);

        String expectedId = this.mockMvc.perform(post("/agency/ads/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "name")
                .param("email", "email")
                .param("phone", "phone")
                .param("area", "100.0")
                .param("numOfRooms", "4")
                .param("cost", "10000")
                .param("floor", "10")
                .param("city", "city")
                .param("street", "street")
                .param("houseNum", "houseNum"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(expectedId).isNotEqualTo(1000L);
    }
}