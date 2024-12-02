package com.example.spring.product;

import com.example.spring.product.model.Product;

import lombok.Getter;

@Getter
public class UpdateProductCommand {
    private Integer id;
    private Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }
}
