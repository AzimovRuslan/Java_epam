package com.example.springtask.controller;

import com.example.springtask.domain.store.Price;
import com.example.springtask.domain.store.Product;
import com.example.springtask.exceptions.NotFoundException;
import com.example.springtask.repos.PriceRepository;
import com.example.springtask.repos.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/price")
public class PriceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    public PriceController(PriceRepository priceRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Price>> prices() {
        List<Price> prices = priceRepository.findAll();
        return ResponseEntity.ok().body(prices);
    }

    @GetMapping("/{value}")
    public ResponseEntity<List<Price>> getPricesByValue(@PathVariable("value") String value) {
        Pattern priceRangePattern = Pattern.compile("price_range-");
        Matcher priceRangeMatcher = priceRangePattern.matcher(value);

        Pattern priceCurrencyPattern = Pattern.compile("currency-");
        Matcher priceCurrencyMatcher = priceCurrencyPattern.matcher(value);
        List<Price> prices = new ArrayList<>();
        if (priceRangeMatcher.find()) {
            int minPrice = Integer.parseInt(value.split("-")[1]);
            int maxPrice = Integer.parseInt(value.split("-")[2]);

            List<Price> pricesFromDb = priceRepository.findAll();
            for (Price price : pricesFromDb) {
                if (price.getConventionalUnit() > minPrice && price.getConventionalUnit() < maxPrice) {
                    prices.add(price);
                }
            }
        } else if (priceCurrencyMatcher.find()) {
            String currency = value.split("-")[1];

            prices = priceRepository.findAll()
                    .stream()
                    .filter(price -> price.getCurrency().equals(currency))
                    .collect(Collectors.toList());
        } else if (value.matches("[+]?\\d+")) {
            Price price = priceRepository.getById(Long.parseLong(value));

            Price priceForReturn = new Price();
            priceForReturn.setId(price.getId());
            priceForReturn.setProduct(price.getProduct());
            priceForReturn.setConventionalUnit(price.getConventionalUnit());
            priceForReturn.setCurrency(price.getCurrency());

            prices.add(priceForReturn);
        } else {
            prices = priceRepository.findAll()
                    .stream()
                    .filter(price -> price.getProduct().getName().equals(value))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok().body(prices);
    }

    @PostMapping()
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        Price priceForCreate = null;

        Product productFromDb = productRepository.findAll()
                .stream()
                .filter(product -> product.getName().equals(price.getProduct().getName()))
                .findFirst()
                .orElse(null);

        if (productFromDb == null) {
            throw new NotFoundException();
        } else {
            List<Price> pricesFromDb = priceRepository.findAll();
            price.setProduct(productFromDb);

            for (Price p : pricesFromDb) {
                if (p.getProduct().getName().equals(price.getProduct().getName())) {
                    if (p.getCurrency().equals(price.getCurrency())) {
                        priceForCreate = p;
                    }
                }
            }

            if (priceForCreate != null) {
                updatePrice(priceForCreate, price);
            } else {
                priceForCreate = priceRepository.save(price);
            }
        }

        return ResponseEntity.status(201).body(priceForCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(
            @PathVariable("id") Price priceFromDb,
            @RequestBody Price price) {
        price.setProduct(priceFromDb.getProduct());
        BeanUtils.copyProperties(price, priceFromDb, "id");

        return ResponseEntity.ok().body(priceRepository.save(priceFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Price> deletePrice(@PathVariable("id") Long id) {
        Price price = priceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id-" + id));
        priceRepository.deleteById(id);

        return ResponseEntity.ok().body(price);
    }
}
