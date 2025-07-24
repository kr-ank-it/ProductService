package com.ank.productmicroservice.controllers;

import com.ank.productmicroservice.commons.AuthCommons;
import com.ank.productmicroservice.dtos.UserDto;
import com.ank.productmicroservice.exceptions.InvalidTokenException;
import com.ank.productmicroservice.exceptions.ProductNotFoundException;
import com.ank.productmicroservice.models.Product;
import com.ank.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    AuthCommons authCommons;
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId, @RequestHeader("token") String token) throws ProductNotFoundException {
        UserDto userDto = authCommons.validateToken(token);
        if(userDto == null) {
            throw new InvalidTokenException("Token is invalid or expired. Please login again.");
        }
        return new ResponseEntity<>(
                productService.getSingleProduct(productId),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId){
        return null;
    }

}
