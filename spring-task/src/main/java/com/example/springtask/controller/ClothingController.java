package com.example.springtask.controller;

import com.example.springtask.domain.store.Category;
import com.example.springtask.domain.store.Product;
import com.example.springtask.repos.CategoryRepository;
import com.example.springtask.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClothingController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/clothing")
    public String clothing(Model model) {
        Iterable<Product> products = productRepository.findAll();

        model.addAttribute("products", products);
        return "clothing";
    }

    @PostMapping("/clothing")
    public String add(@RequestParam Long category, @RequestParam String name, @RequestParam String description, @RequestParam int price, Model model) {
        Category categoryForProduct = categoryRepository.findById(category).orElse(null);
        Iterable<Product> products = productRepository.findAll();

        if (categoryForProduct != null) {
            Product product = new Product(name, description, price);
            product.setCategory(categoryForProduct);
            productRepository.save(product);
        }
        model.addAttribute("products", products);

        return "redirect:/clothing";
    }
}
