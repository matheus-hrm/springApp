package com.example.spring.product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.product.Command;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.validators.ProductValidator;


@Service
public class CreateProductService implements Command<Product, ProductDTO>{

  private final ProductRepository productRepository;  

  public CreateProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  @Override
  public ResponseEntity<ProductDTO> execute(Product product) {
    ProductValidator.execute(product); 
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
  }


}
