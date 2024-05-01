package com.example.marketdota.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyAuthority('USER','EMPLOYEE','ADMIN')")
public class HomeController {
    @GetMapping("/")
    public String login() {
        return "home";
    }

}