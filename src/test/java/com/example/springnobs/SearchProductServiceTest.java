package com.example.springnobs;

import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_search_product_service_return_product_dto() {
        String name = "sony";
        List<Product> products = List.of(new Product());

        when(productRepository.findByNameOrDescriptionContaining(name)).thenReturn(products);

        ResponseEntity<List<ProductDTO>> response = searchProductService.execute(name);
        List<ProductDTO> expected = products.stream()
                                            .map(ProductDTO::new)
                                            .toList();
        assertEquals(ResponseEntity.ok(expected), response);
        verify(productRepository).findByNameOrDescriptionContaining(name);
    }

    @Test
    public void given_product_does_not_exists_when_search_product_service_return_empty_list() {
        String name = "none";
        List<Product> products = List.of();

        when(productRepository.findByNameOrDescriptionContaining(name)).thenReturn(products);

        ResponseEntity<List<ProductDTO>> response = searchProductService.execute(name);
        List<ProductDTO> expected = List.of();

        assertEquals(ResponseEntity.ok(expected), response);
        verify(productRepository).findByNameOrDescriptionContaining(name);
    }
}
