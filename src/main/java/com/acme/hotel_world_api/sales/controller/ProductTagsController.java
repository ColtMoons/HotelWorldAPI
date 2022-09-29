package com.acme.hotel_world_api.sales.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.service.ProductService;
import com.acme.hotel_world_api.sales.resource.ProductResource;

@RestController
@RequestMapping("/api")
public class ProductTagsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService productService;

    @PostMapping("/products/{productId}/tags/{tagId}")
    public ProductResource assingProductTag(@PathVariable Long productId, @PathVariable Long tagId){
        return convertToResource(productService.assignProductTag(productId, tagId));
    }

    @DeleteMapping("/products/{productId}/tags/{tagId}")
    public ProductResource unassingProductTag(@PathVariable Long productId, @PathVariable Long tagId){
        return convertToResource(productService.unassignProductTag(productId, tagId));
    }

    private ProductResource convertToResource(Product entity){
        return mapper.map(entity, ProductResource.class);
    }
}
