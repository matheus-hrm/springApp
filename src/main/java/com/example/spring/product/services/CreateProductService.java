package com.example.spring.product.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.Command;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.validators.ProductValidator;


@Service
public class CreateProductService implements Command<Product, ProductDTO>{

  private final ProductRepository productRepository;  

  private static final Logger logger = LoggerFactory.getLogger(CreateProductService.class);

  public CreateProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  @Cacheable("productCache")
  public ResponseEntity<ProductDTO> execute(Product product) {
    logger.info("Executing" + getClass() + " with input: " + product);
    ProductValidator.execute(product); 
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
  }
}
