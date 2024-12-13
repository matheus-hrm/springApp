package com.example.springnobs;

import com.example.spring.exceptions.ProductNotValidException;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_when_create_product_service_return_product_dto() {
        Product product = new Product();
        product.setName("product");
        product.setDescription("Product description which is at least 20 characters");
        product.setPrice(100.0);

        when(productRepository.save(product)).thenReturn(product);
        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product)), response);
        assertEquals(new ProductDTO(product), response.getBody());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void given_invalid_product_when_create_product_throw_product_not_valid_exception() {
        Product product = new Product();
        product.setName("product");
        product.setDescription("Product");
        product.setPrice(0.0);

        assertThrows(ProductNotValidException.class, () -> createProductService.execute(product));
        verify(productRepository, times(0)).save(product);
    }
}
