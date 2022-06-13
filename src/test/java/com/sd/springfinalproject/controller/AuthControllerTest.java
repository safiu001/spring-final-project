package com.sd.springfinalproject.controller;

import com.sd.springfinalproject.model.UserModel;
import com.sd.springfinalproject.service.AuthoritiesService;
import com.sd.springfinalproject.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@RunWith(Parameterized.class)
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
    void testAllEndPoints() throws Exception {
        String[] urls = {"/accessDenied", "/registerForm", "/customLoginForm"};
        for(String url:urls)
            mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    void registerTheUser() throws Exception {
        UserModel user = new UserModel("andy", "Andy", "Bernard", "andy123");
        String postUrl = "/registerTheUser";
        mockMvc.perform(MockMvcRequestBuilders.post(postUrl).flashAttr("user", user).with(csrf())).andExpect(status().isOk());
    }
}