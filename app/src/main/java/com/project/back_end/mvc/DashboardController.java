package com.project.back_end.mvc;

import com.project.back_end.services.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

// 1. Set Up the MVC Controller Class:
//    - Annotate the class with `@Controller` to indicate that it serves as an MVC controller returning view names (not JSON).
//    - This class handles routing to admin and doctor dashboard pages based on token validation.
@Controller
public class DashboardController {

    // 2. Autowire the Shared Service:
    private final Service service;  // Assuming Service is a class that handles token validation

    //    - Inject the common `Service` class, which provides the token validation logic used to authorize access to dashboards.
    public DashboardController(Service service) {
        this.service = service;
    }

    // 3. Define the `adminDashboard` Method:
    //    - Handles HTTP GET requests to `/adminDashboard/{token}`.
    //    - Accepts an admin's token as a path variable.
    @GetMapping("/adminDashboard/{token}")
    public String adminDashboard(@PathVariable String token) {
        // Validates the token using the shared service for the "admin" role.
        final ResponseEntity<Map<String, String>> response = service.validateToken(token, "admin");
        if ( response != null && response.getStatusCode().is2xxSuccessful()) {
            // If the token is valid, forwards the user to the "admin/adminDashboard" view.
            return "admin/adminDashboard";
        }

        // If invalid, redirects to the root URL, likely the login or home page.
        return "redirect:/";
    }

    // 4. Define the `doctorDashboard` Method:
    //    - Handles HTTP GET requests to `/doctorDashboard/{token}`.
    //    - Accepts a doctor's token as a path variable.
    @GetMapping("/doctorDashboard/{token}")
    public String doctorDashboard(@PathVariable String token) {
        //    - Validates the token using the shared service for the `"doctor"` role.
        final ResponseEntity<Map<String, String>> response = service.validateToken(token, "doctor");
        if ( response != null && response.getStatusCode().is2xxSuccessful()) {
            //    - If the token is valid, forwards the user to the `"doctor/doctorDashboard"` view.
            return "doctor/doctorDashboard";
        }

        //    - If the token is invalid, redirects to the root URL.
        return "redirect:/";
    }
}
