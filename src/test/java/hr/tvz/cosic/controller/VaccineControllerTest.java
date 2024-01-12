package hr.tvz.cosic.controller;

import hr.tvz.cosic.service.VaccineDTO;
import hr.tvz.cosic.service.VaccineService;
import hr.tvz.cosic.vaccine.Vaccine;
import hr.tvz.cosic.vaccine.VaccineCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class VaccineControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VaccineService vaccineService;

    @Test
    void getAllVaccines() throws Exception {
        when(vaccineService.findAll()).thenReturn(Arrays.asList(
                new VaccineDTO("mRNA-1273", "ModernaTX", Vaccine.VaccineType.RNA, 1, 22),
                new VaccineDTO("BNT162b2", "Pfizer & BioNTech", Vaccine.VaccineType.VIRALNI_VEKTOR, 2, 22),
                new VaccineDTO("JNJ-78436735", "Johnson & Johnson", Vaccine.VaccineType.RNA, 2, 11)
        ));

        this.mockMvc.perform(
                get("/vaccine")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getResearchName() throws Exception {
        when(vaccineService.findByResearchName("mRNA-1273")).thenReturn(Optional.of(
                new VaccineDTO("mRNA-1273", "ModernaTX", Vaccine.VaccineType.RNA, 100, 2)
        ));

        this.mockMvc.perform(
                get("/vaccine/{researchName}", "mRNA-1273")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().isOk());
    }

    @Test
    void getVaccineByInvalidResearchName() throws Exception {
        when(vaccineService.findByResearchName("nepostojece")).thenReturn(Optional.empty()
        );

        this.mockMvc.perform(
                get("/vaccine/{researchName}", "nepostojece")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete() throws Exception {
        vaccineService.deleteByResearchName("mRNA-1273");

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/vaccine/{researchName}", "mRNA-1273")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void updateQuantityInStorage() {
    }

    @Test
    void save() throws Exception {
        when(vaccineService.save(new VaccineCommand("ddd-1273", "StaromodnaTX", 2, 100, Vaccine.VaccineType.RNA))).thenReturn(
                Optional.of(new VaccineDTO("ddd-1273", "StaromodnaTX", Vaccine.VaccineType.RNA, 100, 2))
        );

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/vaccine")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().isCreated());
    }
}
