package com.example.spring.product.services;

import com.example.spring.product.Query;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>>{
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(SearchProductService.class);

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        logger.info("Executing" + getClass() + " with input: " + name);
        return ResponseEntity.ok(productRepository.findByNameOrDescriptionContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList()
        );
    }
}
