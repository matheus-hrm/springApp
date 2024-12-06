package com.example.spring.product.services;

import com.example.spring.product.Query;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>>{
    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok(productRepository.findByNAmeOrDescriptionContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList()
        );
    }
}
