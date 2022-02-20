package com.example.springtask.controller;

import com.example.springtask.domain.store.Category;
import com.example.springtask.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }

    @PostMapping("/category")
    public String add(@RequestParam String name, Model model) {
        Category category = new Category(name);

        categoryRepository.save(category);

        Iterable<Category> categories = categoryRepository.findAll();

        model.addAttribute("categories", categories);

        return "redirect:/category";
    }
}
