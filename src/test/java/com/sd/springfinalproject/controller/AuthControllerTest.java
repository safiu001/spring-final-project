package com.sd.springfinalproject.controller;

import com.sd.springfinalproject.entity.Users;
import com.sd.springfinalproject.service.AuthoritiesService;
import com.sd.springfinalproject.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @MockBean
    private UsersService usersService;

    @MockBean
    private AuthoritiesService authService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @Test
    void customLoginForm() throws Exception {
        String url = "/customLoginForm";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    void registerForm() throws Exception{
        String url = "/registerForm";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    void registerTheUser() throws Exception {
        Users user = new Users("andy", "andy123", true, "Andy", "Bernard");
        String url = "/registerTheUser";
        mockMvc.perform(MockMvcRequestBuilders.post(url).flashAttr("user", user).with(csrf())).andExpect(status().isOk());
    }

    @Test
    void accessDenied() throws Exception {
        String url = "/accessDenied";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }
}