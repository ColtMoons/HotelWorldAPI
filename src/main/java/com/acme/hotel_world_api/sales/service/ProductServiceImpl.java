package com.acme.hotel_world_api.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.model.Tag;
import com.acme.hotel_world_api.sales.domain.repository.ProductRepository;
import com.acme.hotel_world_api.sales.domain.repository.SaleRepository;
import com.acme.hotel_world_api.sales.domain.repository.TagRepository;
import com.acme.hotel_world_api.sales.domain.service.ProductService;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProductsByHotelId(Long hotelId, Pageable pageable) {
       return hotelRepository.findById(hotelId).map(hotel->{
        List<Product> products =hotel.getProducts();
        int productCount = products.size();
        return new PageImpl<>(products, pageable, productCount);
       }).orElseThrow(()->new ResourceNotFoundException("Hotel", "ID", hotelId));
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product productDetails) {
        return productRepository.findById(productId).map(product->{
            product.setName(productDetails.getName()).setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        return productRepository.findById(productId).map(product->{
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));
    }

    @Override
    public Product assignProductTag(Long productId, Long tagId) {
        Tag tag=tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "ID", tagId));

        return productRepository.findById(productId).map(product->
            productRepository.save(product.tagWith(tag))
        ).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));
    }

    @Override
    public Product unassignProductTag(Long productId, Long tagId) {
        Tag tag=tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "ID", tagId));

        return productRepository.findById(productId).map(product->
            productRepository.save(product.unTagWith(tag))
        ).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));
    }

    @Override
    public Page<Product> getAllPostByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(tag->{
            List<Product> products = tag.getProducts();
            int productsCount=products.size();
            return new PageImpl<>(products, pageable, productsCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Tag", "ID", tagId));
    }

    @Override
    public Page<Product> getAllProductsBySaleId(Long saleId, Pageable pageable) {
        return saleRepository.findById(saleId).map(sale ->{
            List<Product> products = sale.getProducts();
            int productCount= products.size();
            return new PageImpl<>(products, pageable, productCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Sale", "ID", saleId));
    }
    
}
