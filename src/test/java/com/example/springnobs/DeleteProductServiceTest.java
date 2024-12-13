package com.example.springnobs;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.services.DeleteProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_delete_product_service_return_no_content_status() {
        Product product = new Product();
        product.setId(1);
        product.setName("product");
        product.setDescription("Product description which is at least 20 characters");
        product.setPrice(100.0);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        deleteProductService.execute(1);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void given_product_does_not_exists_when_delete_product_service_throw_product_not_found_exception() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }
}
