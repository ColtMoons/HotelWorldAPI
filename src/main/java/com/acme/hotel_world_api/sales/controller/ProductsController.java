package com.acme.hotel_world_api.sales.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.service.ProductService;
import com.acme.hotel_world_api.sales.resource.ProductResource;
import com.acme.hotel_world_api.sales.resource.SaveProductResource;


@RestController
@RequestMapping("/api")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable){
        List<ProductResource> products = productService.getAllProducts(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int productsCount = products.size();
        return new PageImpl<>(products, pageable, productsCount);

    }   

    @GetMapping("/hotels/{hotelId}/products")
    public Page<ProductResource> getAllProductsByHotelId(@PathVariable Long hotelId, Pageable pageable){
        List<ProductResource> products=productService.getAllProductsByHotelId(hotelId, pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int productsCount=products.size();
        return new PageImpl<>(products, pageable,productsCount);
    }
    @GetMapping("/sales/{saleId}/products")
    public Page<ProductResource> getAllProductsBySaleId(@PathVariable Long saleId, Pageable pageable){
        List<ProductResource> products= productService.getAllProductsBySaleId(saleId, pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int productsCount = products.size();
        return new PageImpl<>(products, pageable, productsCount);
    }

    @GetMapping("/products/{productId}")
    public ProductResource getProductById(@PathVariable Long productId){
        return convertToResource(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.createProduct(convertToEntity(resource)));
    }

    @PutMapping("products/{productId}")
    public ProductResource updateProduct(@PathVariable Long productId, @Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.updateProduct(productId, convertToEntity(resource)));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }

    private Product convertToEntity(SaveProductResource resource){
        return mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity){
        return mapper.map(entity, ProductResource.class);
    }
}
