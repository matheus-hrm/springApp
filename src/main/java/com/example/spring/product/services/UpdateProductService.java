package com.example.spring.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.Command;
import com.example.spring.product.UpdateProductCommand;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.model.ProductRepository;
import com.example.spring.product.validators.ProductValidator;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO>{

  private ProductRepository productRepository;
  
  public UpdateProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
    Optional<Product> producOptional = productRepository.findById(command.getId());
    if (producOptional.isPresent()) {
      Product product = producOptional.get();
      product.setId(command.getId());
      ProductValidator.execute(product);
      productRepository.save(product);

      return ResponseEntity.ok(new ProductDTO(product));
    }

    throw new ProductNotFoundException();
  }
}
