package com.acme.hotel_world_api.sales.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.sales.domain.model.Product;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> getAllProductsByHotelId(Long hotelId, Pageable pageable);
    Page<Product> getAllProductsBySaleId(Long saleId, Pageable pageable);
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product productDetails);
    ResponseEntity<?> deleteProduct(Long productId);
    Product assignProductTag(Long productId, Long tagId);
    Product unassignProductTag(Long productId, Long tagId);
    Page<Product> getAllPostByTagId(Long tagId, Pageable pageable);
}
