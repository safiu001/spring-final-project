package com.sd.springfinalproject.controller;

import com.sd.springfinalproject.entity.Authorities;
import com.sd.springfinalproject.entity.Users;
import com.sd.springfinalproject.service.AuthoritiesService;
import com.sd.springfinalproject.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UsersService usersService;
    private AuthoritiesService authoritiesService;

    public AuthController(UsersService usersService, AuthoritiesService authoritiesService){
        this.usersService = usersService;
        this.authoritiesService = authoritiesService;
    }

    @GetMapping("/customLoginForm")
    public String customLoginForm(){
        return "login";
    }

    @GetMapping("/registerForm")
    public String registerForm(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/registerTheUser")
    public String registerTheUser(@ModelAttribute("user") Users user){
        usersService.save(user, true);

        String role = "ROLE_USER";
        Authorities authorities = new Authorities(role, user, user.getUsername());
        authoritiesService.save(authorities);
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }
}
