package com.example.spring.product.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.Command;
import com.example.spring.product.UpdateProductCommand;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.validators.ProductValidator;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO>{

  private final ProductRepository productRepository;

  private static final Logger logger = LoggerFactory.getLogger(UpdateProductService.class);

  public UpdateProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
    logger.info("Executing" + getClass() + " with input: " + command);
    Optional<Product> productOptional = productRepository.findById(command.getId());
    if (productOptional.isPresent()) {
      Product product = productOptional.get();
      product.setId(command.getId());
      ProductValidator.execute(product);
      productRepository.save(product);

      return ResponseEntity.ok(new ProductDTO(product));
    }

    throw new ProductNotFoundException();
  }
}
