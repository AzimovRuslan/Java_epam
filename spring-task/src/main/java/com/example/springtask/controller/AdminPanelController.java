package com.example.springtask.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelController {
    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }
}
