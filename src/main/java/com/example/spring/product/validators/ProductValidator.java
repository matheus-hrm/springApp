package com.example.spring.product.validators;

import com.example.spring.exceptions.ErrorMessages;
import com.example.spring.exceptions.ProductNotValidException;
import com.example.spring.product.model.Product;

import io.micrometer.common.util.StringUtils;

public class ProductValidator {
    private ProductValidator() {
    }

    public static void execute(Product product) {
        if (StringUtils.isEmpty(product.getName())) {
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }

        if (product.getDescription().length() < 20) {
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_LENGTH.getMessage());
        }

        if (product.getPrice() == null || product.getPrice() < 0.00) {
            throw new ProductNotValidException(ErrorMessages.PRICE_NOT_NEGATIVE.getMessage());
        }
    }
}
