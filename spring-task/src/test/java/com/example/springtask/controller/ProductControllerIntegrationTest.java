package com.example.springtask.controller;

import com.example.springtask.domain.store.Category;
import com.example.springtask.domain.store.Price;
import com.example.springtask.domain.store.Product;
import com.example.springtask.repos.CategoryRepository;
import com.example.springtask.repos.PriceRepository;
import com.example.springtask.repos.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void resetDb() {
        priceRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void whenCreateProduct_thenStatus201() {
        Category superCategory = new Category("Outwear");
        ResponseEntity<Category> responseSuperCategory = restTemplate.postForEntity("/category", superCategory, Category.class);

        Set<Category> categories = new HashSet<>();
        categories.add(superCategory);

        Category category = new Category("Jackets");
        category.setSuperCategories(categories);
        ResponseEntity<Category> responseCategory = restTemplate.postForEntity("/category", category, Category.class);

        Product product = new Product("Jacket");
        product.setCategory(category);
        ResponseEntity<Product> responseProduct = restTemplate.postForEntity("/product", product, Product.class);

        assertThat(responseProduct.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(Objects.requireNonNull(responseProduct.getBody()).getId(), notNullValue());
        assertThat(responseProduct.getBody().getName(), is("Jacket"));
        assertThat(responseProduct.getBody().getCategory().getName(), is("Jackets"));
    }

    @Test
    void whenGetProductById_thenStatus200() {
        Category category = new Category("Jackets");
        Long id = createTestProduct(category, "Jacket").getId();
        Product[] products = restTemplate.getForObject("/product/{id}", Product[].class, id);

        assertThat(products[0].getName(), is("Jacket"));
    }

    private Product createTestProduct(Category category, String name) {
        categoryRepository.save(category);
        Product product = new Product(name);
        product.setCategory(category);
        return productRepository.save(product);
    }
}
