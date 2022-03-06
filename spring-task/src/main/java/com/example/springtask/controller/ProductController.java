package com.example.springtask.controller;

import com.example.springtask.domain.store.Category;
import com.example.springtask.domain.store.Price;
import com.example.springtask.domain.store.Product;
import com.example.springtask.exceptions.NotFoundException;
import com.example.springtask.repos.CategoryRepository;
import com.example.springtask.repos.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> categories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        Page<Product> products = productRepository.findAll(
                PageRequest.of(page.orElse(0),
                        10,
                        Sort.Direction.ASC, sortBy.orElse("id")));
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{value}")
    public ResponseEntity<List<Product>> getProductsByValue(@PathVariable("value") String value) {
        Pattern categoryIdPattern = Pattern.compile("category_id-");
        Matcher categoryIdMatcher = categoryIdPattern.matcher(value);
        List<Product> products = new ArrayList<>();

        if (value.matches("[+]?\\d+")) {
            Product product = productRepository.getById(Long.parseLong(value));

            Product productForReturn = new Product();
            productForReturn.setId(product.getId());
            productForReturn.setCategory(product.getCategory());
            productForReturn.setName(product.getName());

            products.add(productForReturn);
        } else if (categoryIdMatcher.find()) {
            Long categoryId = Long.parseLong(value.split("-")[1]);

            products = productRepository.findAll()
                    .stream()
                    .filter(product -> product.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
        } else {
            products = productRepository.findAll()
                    .stream()
                    .filter(product -> product.getName().equals(value))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok().body(products);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productForCreate = null;

        Category categoryFromDb = categoryRepository.findAll()
                .stream()
                .filter(c -> c.equals(product.getCategory()))
                .findFirst()
                .orElse(null);

        if (categoryFromDb == null) {
            throw new NotFoundException();
        } else {
            product.setCategory(categoryFromDb);
            productForCreate = productRepository.save(product);
        }

        return ResponseEntity.status(201).body(productForCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Product productFromDb,
            @RequestBody Product product) {
        product.setCategory(productFromDb.getCategory());
        BeanUtils.copyProperties(product, productFromDb, "id");

        return ResponseEntity.ok().body(productRepository.save(productFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id-" + id));
        productRepository.deleteById(id);

        return ResponseEntity.ok().body(product);
    }
}
