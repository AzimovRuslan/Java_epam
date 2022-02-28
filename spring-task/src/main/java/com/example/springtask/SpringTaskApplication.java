package com.example.springtask;
//
//import com.example.springtask.domain.security.Role;
//import com.example.springtask.domain.security.User;
//import com.example.springtask.domain.store.Category;
//import com.example.springtask.repos.CategoryRepository;
//import com.example.springtask.repos.UserRepository;

import com.example.springtask.domain.store.Category;
import com.example.springtask.domain.store.Price;
import com.example.springtask.domain.store.Product;
import com.example.springtask.repos.CategoryRepository;
import com.example.springtask.repos.PriceRepository;
import com.example.springtask.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskApplication.class, args);
    }
//
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ProductRepository productRepository;

//    @Bean
//    public CommandLineRunner CommandLineRunnerBean() {
//        return (args) -> {
//            User admin = new User();
//            admin.setUsername("admin");
//            admin.setPassword("admin");
//            admin.setActive(true);
//            admin.setRoles(Collections.singleton(Role.ADMIN));
//            userRepository.save(admin);
//        };
//    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean1() {
        return (args) -> {
            Category superCategory = new Category("Outwear");
            categoryRepository.save(superCategory);
            Category category = new Category("Jackets");
            category.addSuperCategory(superCategory);
            categoryRepository.save(category);

            Product product = new Product();
            product.setName("Jacket");
            product.setCategory(category);
            productRepository.save(product);

            Price price = new Price(100, "BYN");
            price.setProduct(product);
            priceRepository.save(price);
        };
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean2() {
        return (args) -> {
            Category superCategory = new Category("Shoes");
            categoryRepository.save(superCategory);
            Category category = new Category("Sneakers");
            category.addSuperCategory(superCategory);
            categoryRepository.save(category);

            Product product = new Product();
            product.setName("Sneakers PUMA");
            product.setCategory(category);
            productRepository.save(product);

            Price price = new Price(300, "USD");
            price.setProduct(product);
            priceRepository.save(price);
        };
    }
}
