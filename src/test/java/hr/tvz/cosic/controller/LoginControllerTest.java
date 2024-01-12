package hr.tvz.cosic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;


//    @Test
//    void authenticate() {
//        this.mockMvc.perform(
//                post("/authenticate")
//                .with(user("admin")
//                .password("test")
//                .roles("admin")
//        )
//                .with(csrf())
//                .contentType()
//    }
}
