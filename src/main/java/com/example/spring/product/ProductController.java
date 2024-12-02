package com.example.spring.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.CreateProductService;
import com.example.spring.product.services.DeleteProductService;
import com.example.spring.product.services.GetProductService;
import com.example.spring.product.services.GetProductsService;
import com.example.spring.product.services.UpdateProductService;


@RestController
public class ProductController {

  private final CreateProductService createProductService;

  private final GetProductsService getProductsService;

  private final GetProductService getProductService;

  private final UpdateProductService updateProductService;

  private final DeleteProductService deleteProductService;
    
  public ProductController(CreateProductService createProductService,
                           GetProductsService getProductsService,
                           UpdateProductService updateProductService, 
                           DeleteProductService deleteProductService,
                           GetProductService getProductService) {
    this.createProductService = createProductService;
    this.getProductsService = getProductsService;
    this.updateProductService = updateProductService;
    this.deleteProductService = deleteProductService;
    this.getProductService = getProductService;
  }

  @PostMapping("/product")
  public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
    return createProductService.execute(product);
  }

  @GetMapping("/products")
  public ResponseEntity<List<ProductDTO>> getProducts() {
    return getProductsService.execute(null);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductDTO> getProductbyId(@PathVariable Integer id) {
    return getProductService.execute(id);
  }

  @PutMapping("/product/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
    return updateProductService.execute(new UpdateProductCommand(id, product));
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    return deleteProductService.execute(id);
  }

}
