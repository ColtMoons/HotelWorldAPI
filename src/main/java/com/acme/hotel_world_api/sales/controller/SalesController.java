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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.sales.domain.model.Sale;
import com.acme.hotel_world_api.sales.domain.service.SaleService;
import com.acme.hotel_world_api.sales.resource.SaleResource;
import com.acme.hotel_world_api.sales.resource.SaveSaleResource;

@RestController
@RequestMapping("/api")
public class SalesController {
    
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SaleService saleService;

    @GetMapping("/hotels/{hotelId}/sales")
    private Page<SaleResource> getAllSalesByHotelId(@PathVariable Long hotelId, Pageable pageable){
        List<SaleResource> sales=saleService.getAllSalesByHotelId(hotelId, pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        int salesCount=sales.size();
        return new PageImpl<>(sales, pageable, salesCount);
    }

    @GetMapping("/hotels/{hotelId}/sales/{saleId}")
    public SaleResource getSaleByIdAndHotelId(@PathVariable Long hotelId, @PathVariable Long saleId){
        return convertToResource(saleService.getSaleByIdAndHotelId(hotelId, saleId));
    }

    @PostMapping("/hotels/{hotelId}/sales")
    private SaleResource createSale(@PathVariable Long hotelId,@Valid @RequestBody SaveSaleResource resource){
        return convertToResource(saleService.createSale(hotelId, convertToEntity(resource)));
    }

    @DeleteMapping("/hotel/{hotelId}/sales/{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable Long hotelId, @PathVariable Long saleId){
        return saleService.deleteSale(hotelId, saleId);
    }



    private Sale convertToEntity(SaveSaleResource resource){
        return mapper.map(resource, Sale.class);
    }

    private SaleResource convertToResource(Sale entity){
        return mapper.map(entity, SaleResource.class);
    }
}
