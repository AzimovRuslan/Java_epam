//package com.example.springtask.controller;
//
//import com.example.springtask.domain.security.Role;
//import com.example.springtask.domain.security.User;
//import com.example.springtask.repos.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Collections;
//
//@Controller
//@RequestMapping("registration")
//public class RegistrationController {
//    private final UserRepository userRepository;
//
//    public RegistrationController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping
//    public String registration() {
//        return "registration";
//    }
//
//    @PostMapping
//    public String registerUser(User user, Model model) {
//        User userFromDb = userRepository.findByUsername(user.getUsername());
//
//        if (userFromDb != null) {
//            model.addAttribute("message", "Such user already exists");
//            return "registration";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepository.save(user);
//
//        return "redirect:/login";
//    }
//}
