package com.auth.service.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping("/secure")
    public String secure() {
        return "This is a secured endpoint!";
    }
    
    @GetMapping("/user/dashboard")
    public String userDashboard() {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello "+authentication.getName()+" Welcome to User Dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Admin Dashboard";
    }
}
