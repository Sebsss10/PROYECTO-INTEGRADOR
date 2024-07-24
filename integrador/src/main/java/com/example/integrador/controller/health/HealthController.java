package com.example.integrador.controller.health;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
@RestController
public class HealthController {

    @RequestMapping("/health")
    public String checkAPI() {
        return "<h1>The API is working ok!</h1>";
    }
}
