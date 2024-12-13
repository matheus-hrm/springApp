package com.example.springnobs;

import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetProductsServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_products_exists_when_get_products_service_return_product_dtos() {
        // given
        Product product = new Product();
        product.setId(1);
        product.setName("product");
        product.setDescription("Product description which is at least 20 characters");
        product.setPrice(100.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(200.0);

        List<Product> products = Arrays.asList(product, product2);
        when(productRepository.findAll()).thenReturn(products);
        // when
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);

        // then
        List<ProductDTO> expected = products.stream().map(ProductDTO::new).toList();
        assertEquals(ResponseEntity.ok(expected), response);
        verify(productRepository, times(1)).findAll();
    }
}
