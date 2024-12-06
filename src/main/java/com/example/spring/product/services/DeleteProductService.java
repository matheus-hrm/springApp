package com.example.spring.product.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.Command;
import com.example.spring.product.model.Product;
import com.example.spring.product.ProductRepository;

@Service
public class DeleteProductService implements Command<Integer, Void> {

  private final ProductRepository productRepository;

  public DeleteProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer id) {
    Optional<Product> producOptional = productRepository.findById(id);
    if (producOptional.isPresent()) {
      productRepository.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  
    throw new ProductNotFoundException();
  }
}
