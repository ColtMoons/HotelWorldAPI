package com.acme.hotel_world_api.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.model.Sale;
import com.acme.hotel_world_api.sales.domain.repository.ProductRepository;
import com.acme.hotel_world_api.sales.domain.repository.SaleRepository;
import com.acme.hotel_world_api.sales.domain.service.SaleService;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;

@Repository
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Sale> getAllSalesByHotelId(Long hotelId, Pageable pageable) {
        return saleRepository.findByHotelId(hotelId, pageable);
    }

    @Override
    public Sale getSaleByIdAndHotelId(Long hotelId, Long saleId) {
        return saleRepository.findByIdAndHotelId(saleId, hotelId).orElseThrow(()->new ResourceNotFoundException("Sale not found with id"+saleId+"and with hotel id"+hotelId));
    }

    @Override
    public Sale createSale(Long hotelId, Sale sale) {
        return hotelRepository.findById(hotelId).map(hotel->{
            sale.setHotel(hotel);
            return saleRepository.save(sale);    
        }).orElseThrow(()->new ResourceNotFoundException("Hotel", "Id",hotelId));
    }

    @Override
    public ResponseEntity<?> deleteSale(Long hotelId, Long saleId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }
        return saleRepository.findById(saleId).map(sale->{
            saleRepository.delete(sale);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "Sale", "Id", saleId
        ));
    }

    @Override
    public Sale addProduct(Long saleId, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(
            "Product", "Id", productId
        ));

        return saleRepository.findById(saleId).map(sale ->saleRepository.save(sale.addProduct(product))).orElseThrow(() -> new ResourceNotFoundException(
            "Sale", "Id", saleId
        ));
    }

    @Override
    public Sale deleteProduct(Long saleId, Long productId) {
        Product product = productRepository.findById(saleId).orElseThrow(() -> new ResourceNotFoundException(
            "Product", "Id", productId
        ));

        return saleRepository.findById(saleId).map(sale ->saleRepository.save(sale.deleteProduct(product))).orElseThrow(() -> new ResourceNotFoundException(
            "Sale", "Id", saleId
        ));
    }
    
}
