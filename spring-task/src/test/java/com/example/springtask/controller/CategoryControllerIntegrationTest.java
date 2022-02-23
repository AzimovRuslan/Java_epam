package com.example.springtask.controller;

import com.example.springtask.domain.store.Category;
import com.example.springtask.repos.CategoryRepository;
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
class CategoryControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void resetDb() {
        categoryRepository.deleteAll();
    }

    @Test
    void whenCreateCategory_thenStatus201() {
        Category superCategory = new Category("Outwear");
        ResponseEntity<Category> responseSuperCategory = restTemplate.postForEntity("/category", superCategory, Category.class);

        assertThat(responseSuperCategory.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(Objects.requireNonNull(responseSuperCategory.getBody()).getName(), is("Outwear"));

        Set<Category> categories = new HashSet<>();
        categories.add(superCategory);

        Category category = new Category("Jacket");
        category.setSuperCategories(categories);
        ResponseEntity<Category> responseCategory = restTemplate.postForEntity("/category", category, Category.class);

        assertThat(responseCategory.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(Objects.requireNonNull(responseCategory.getBody()).getId(), notNullValue());
        assertThat(responseCategory.getBody().getName(), is("Jacket"));
        assertThat(responseCategory.getBody().getSuperCategories().iterator().next().getName(), is("Outwear"));
    }

    @Test
    void whenGetCategoryById_thenStatus200() {
        Long id = createTestCategory("Clothing").getId();
        Category[] category = restTemplate.getForObject("/category/{id}", Category[].class, id);

        assertThat(category[0].getName(), is("Clothing"));
    }

    @Test
    void whenGetCategoryByValue_thenStatus200() {
        String value = createTestCategory("Outwear").getName();
        Category[] category = restTemplate.getForObject("/category/{value}", Category[].class, value);

        assertThat(category[0].getName(), is("Outwear"));
    }

    @Test
    void whenUpdateCategory_thenStatus200() {
        Long id = createTestCategory("Jackets").getId();
        Category category = new Category("Outwear");
        HttpEntity<Category> entity = new HttpEntity<>(category);

        ResponseEntity<Category> responseEntity = restTemplate.exchange("/category/{id}", HttpMethod.PUT, entity,
                Category.class, id);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Outwear"));
    }

    @Test
    void givenCategory_whenDeleteCategory_thenStatus200() {
        Long id = createTestCategory("Jackets").getId();
        ResponseEntity<Category> responseEntity = restTemplate.exchange("/category/{id}", HttpMethod.DELETE, null,
                Category.class, id);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Jackets"));
    }

    @Test
    void whenGetCategories_thenStatus200() {
        createTestCategory("Outwear");
        createTestCategory("Underwear");
        ResponseEntity<List<Category>> responseEntity = restTemplate.exchange("/category", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Category>>() {
                });

        List<Category> categories = responseEntity.getBody();

        assertThat(categories.get(0).getName(), is("Outwear"));
        assertThat(categories.get(1).getName(), is("Underwear"));
    }

    private Category createTestCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }
}
