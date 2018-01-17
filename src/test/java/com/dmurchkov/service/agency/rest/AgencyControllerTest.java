package com.dmurchkov.service.agency.rest;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.model.Ad;
import com.dmurchkov.service.agency.model.Apartment;
import com.dmurchkov.service.agency.model.Author;
import com.dmurchkov.service.agency.persistence.Storage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private Storage storage;

    @MockBean
    private AgencyService agencyService;

    private final long mocked_id = 100L;

    @Test
    public void shouldSubmitNewAdd() throws Exception {
        when(agencyService.submitAdd(anyLong(), anyLong(), anyObject())).thenReturn(mocked_id);
        this.mockMvc.perform(post("/agency/ad")
                .param("authorId", "100")
                .param("apartmentId", "100")
                .param("description", "description"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAdById() throws Exception {
        when(agencyService.getAdById(anyLong())).thenReturn(new Ad());
        this.mockMvc.perform(get("/agency/ad/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAdds() throws Exception {
        when(agencyService.getAdds()).thenReturn(Collections.emptyMap());
        this.mockMvc.perform(get("/agency/ads"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateAuthor() throws Exception {
        when(agencyService.createAuthor(anyString(), anyString(), anyString())).thenReturn(mocked_id);
        this.mockMvc.perform(post("/agency/author")
                .param("name", "name")
                .param("email", "email")
                .param("phone", "phone"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAuthorById() throws Exception {
        when(agencyService.getAuthorById(anyLong())).thenReturn(new Author());
        this.mockMvc.perform(get("/agency/author/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAuthors() throws Exception {
        when(agencyService.getAuthors()).thenReturn(Collections.emptyMap());
        this.mockMvc.perform(get("/agency/authors"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateApartment() throws Exception {
        when(agencyService
                .createApartment(anyDouble(), anyInt(), anyLong(), anyInt(), anyString(), anyString(), anyString()))
                .thenReturn(mocked_id);
        this.mockMvc.perform(post("/agency/apartment")
                .param("area", "100.0")
                .param("numOfRooms", "3")
                .param("cost", "10000")
                .param("floor", "10")
                .param("city", "city")
                .param("street", "street")
                .param("houseNum", "houseNum"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnApartmentById() throws Exception {
        when(agencyService.getApartmentById(anyLong())).thenReturn(new Apartment());
        this.mockMvc.perform(get("/agency/apartment/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnApartments() throws Exception {
        when(agencyService.getApartments()).thenReturn(Collections.emptyMap());
        this.mockMvc.perform(get("/agency/apartments"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowNoSuchEntityExceptionIfAuthorIsNull() throws Exception {
        when(storage.getAuthorById(anyLong())).thenReturn(null);
        this.mockMvc.perform(get("agency/author/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowNoSuchEntityExceptionIfApartmentIsNull() throws Exception {
        when(storage.getApartmentById(anyLong())).thenReturn(null);
        this.mockMvc.perform(get("agency/apartment/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowNoSuchEntityExceptionIfAddIsNull() throws Exception {
        when(storage.getAdById(anyLong())).thenReturn(null);
        this.mockMvc.perform(get("agency/ad/10"))
                .andExpect(status().isNotFound());
    }
}