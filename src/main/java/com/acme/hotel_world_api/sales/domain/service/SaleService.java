package com.acme.hotel_world_api.sales.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.sales.domain.model.Sale;

public interface SaleService {
    //relation with hotel
    Page<Sale> getAllSalesByHotelId(Long hotelId, Pageable pageable);
    Sale getSaleByIdAndHotelId(Long hotelId, Long saleId);
    Sale createSale(Long hotelId, Sale sale);
    ResponseEntity<?> deleteSale(Long hotelId, Long saleId);
    //relation with product
    Sale addProduct(Long saleId, Long productId);
    Sale deleteProduct(Long saleId, Long productId);
}
