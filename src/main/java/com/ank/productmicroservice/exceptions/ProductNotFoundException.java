package com.ank.productmicroservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{
    private Long productId;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }
}
