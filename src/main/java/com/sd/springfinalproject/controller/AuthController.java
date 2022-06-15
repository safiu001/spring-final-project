package com.sd.springfinalproject.controller;

import com.sd.springfinalproject.entity.Authorities;
import com.sd.springfinalproject.entity.Users;
import com.sd.springfinalproject.model.UserModel;
import com.sd.springfinalproject.service.AuthoritiesService;
import com.sd.springfinalproject.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

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
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/registerTheUser")
    public String registerTheUser(@Valid @ModelAttribute("user") UserModel userModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }else{
            Users user = new Users();
            user.setUsername(userModel.getUsername());
            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            user.setPassword(userModel.getPassword());
            usersService.save(user, true);

            String role = "ROLE_USER";
            Authorities authorities = new Authorities(role, user, user.getUsername());
            authoritiesService.save(authorities);
            return "login";
        }
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }
}
