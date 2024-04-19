package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final String COUNTER = "COUNTER";
    private static final String ROLE = "ROLE";

    @GetMapping("/")
    public String home(HttpSession session) {
        incrementCounter(session);
        return "Hello, World!";
    }

    @GetMapping("/count")
    public String count(HttpSession session) {
        return "Counter: " + session.getAttribute(COUNTER);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(HttpSession session) {
        // same way to set admin id or any other admin information
        session.setAttribute(ROLE, "ADMIN");
        System.out.println("Role " + session.getAttribute(ROLE));
        return "Admin page";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user(HttpSession session) {
        // same way to set user id or any other user information
        session.setAttribute(ROLE, "USER");
        System.out.println("Role " + session.getAttribute(ROLE));
        return "USER page";
    }

    private void incrementCounter(HttpSession session) {
        Integer counter = (Integer) session.getAttribute(COUNTER);
        if (counter == null) {
            counter = 0;
        }
        session.setAttribute(COUNTER, counter + 1);
    }
}
