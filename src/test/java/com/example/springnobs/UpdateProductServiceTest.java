package com.example.springnobs;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.UpdateProductCommand;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.UpdateProductService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_update_product_service_return_product_dto() {

        Product product = new Product();
        product.setId(1);
        product.setName("product");
        product.setDescription("Product description which is at least 20 characters");
        product.setPrice(100.0);


        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("new product");
        newProduct.setDescription("New product description which is at least 20 characters");
        newProduct.setPrice(200.0);

        UpdateProductCommand updateProductCommand = new UpdateProductCommand(1, newProduct);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.save(newProduct)).thenReturn(newProduct);

        ResponseEntity<ProductDTO> response = updateProductService.execute(updateProductCommand);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(response.getBody(), new ProductDTO(newProduct));
        verify(productRepository).findById(1);
        verify(productRepository).save(newProduct);
    }

    @Test
    public void given_product_does_not_exists_when_update_product_service_throw_product_not_found_exception() {
        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("new product");
        newProduct.setDescription("New product description which is at least 20 characters");
        newProduct.setPrice(200.0);

        UpdateProductCommand updateProductCommand = new UpdateProductCommand(1, newProduct);
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        ProductNotFoundException exception =  assertThrows(
                ProductNotFoundException.class,
                () -> updateProductService.execute(updateProductCommand)
        );
        verify(productRepository).findById(1);

    }
}
