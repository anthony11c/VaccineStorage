package hr.tvz.cosic.controller;

import hr.tvz.cosic.service.SideEffectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class SideEffectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllSideEffects() throws Exception {
        this.mockMvc.perform(
                get("/side-effect")
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
    void getSideEffectsByVaccineResearchName() throws Exception {
        this.mockMvc.perform(
                get("/side-effect")
                        .param("vaccineResearchName", "JNJ-78436735")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
