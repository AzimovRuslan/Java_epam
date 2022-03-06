package com.example.springtask.utils;

import com.example.springtask.domain.store.Category;
import com.example.springtask.domain.store.Price;
import com.example.springtask.domain.store.Product;
import com.example.springtask.repos.CategoryRepository;
import com.example.springtask.repos.PriceRepository;
import com.example.springtask.repos.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializationDb implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;
    private ProductRepository productRepository;

    public InitializationDb(CategoryRepository categoryRepository, PriceRepository priceRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < Constants.CATEGORIES.length; i++) {
            initializationCategories(Constants.CATEGORIES[i]);
        }

        List<Category> categoryList = categoryRepository.findAll();

        for (int i = 0; i < 100; i++) {
            initializationProducts(categoryList.get(randomNumber(categoryList.size())), "product №" + i);
        }

        List<Product> productList = productRepository.findAll();

        for (int i = 0; i < productList.size(); i++) {
            initializationPrices(productList.get(i), randomNumber(1000), "BYN");
        }
    }

    private void initializationProducts(Category category, String name) {
        Product product = new Product(name);
        product.setCategory(category);
        productRepository.save(product);
    }

    private void initializationPrices(Product product, int conventionalUnit, String currency) {
        Price price = new Price(conventionalUnit, currency);
        price.setProduct(product);
        priceRepository.save(price);
    }

    private void initializationCategories(String name) {
        Category category = new Category(name);
        categoryRepository.save(category);
    }

    private int randomNumber(int finishNumber) {
        int startNumber = 0;

        return (startNumber + (int) (Math.random() * finishNumber));
    }
}
