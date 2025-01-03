package com.example.userserviceapidesign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }
    @GetMapping("/user")
    public String getUser() {
        return "User";

    }
    @GetMapping("/admin")
    public String getAdmin() {
        return "Admin";

    }
}
