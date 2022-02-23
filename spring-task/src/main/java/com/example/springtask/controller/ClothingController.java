//package com.example.springtask.controller;
//
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ClothingController {
////    @Autowired
////    private ProductRepository productRepository;
////
////    @Autowired
////    private CategoryRepository categoryRepository;
////
////    @GetMapping("/clothing")
////    public String clothing(Model model) {
////        Iterable<Product> products = productRepository.findAll();
////
////        model.addAttribute("products", products);
////        return "clothing";
////    }
////
////    @PostMapping("/clothing")
////    public String add(@RequestParam Long category, @RequestParam String name, @RequestParam String description, @RequestParam int price, Model model) {
////        Category categoryForProduct = categoryRepository.findById(category).orElse(null);
////        Iterable<Product> products = productRepository.findAll();
////
////        if (categoryForProduct != null) {
////            Product product = new Product(name, description, price);
////            product.setCategory(categoryForProduct);
////            productRepository.save(product);
////        }
////        model.addAttribute("products", products);
////
////        return "redirect:/clothing";
////    }
//}
