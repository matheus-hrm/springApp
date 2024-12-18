package com.example.spring.product.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.Query;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.ProductRepository;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {

  private final ProductRepository productRepository;

  private static final Logger logger = LoggerFactory.getLogger(GetProductsService.class);

  public GetProductsService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<List<ProductDTO>> execute(Void input) {

    logger.info("Executing" + getClass() + " with input: " + input);
    List<Product> products = productRepository.findAll();
    List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();

    return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
  }
}
