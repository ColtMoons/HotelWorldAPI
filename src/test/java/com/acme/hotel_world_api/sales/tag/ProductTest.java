package com.acme.hotel_world_api.sales.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.acme.hotel_world_api.sales.domain.model.Sale;
import com.acme.hotel_world_api.system.domain.model.Hotel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.model.Tag;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {
    Product product;
    @Test
    @DisplayName("Valid That hotel")
    public  void testHotel(){
        product = new Product();
        List<Hotel>hotels = new ArrayList<>();
        List<Sale>sales = new ArrayList<>();
        List<Tag>tags = new ArrayList<>();
        product.setId(16L)
                .setName("breakfast")
                .setPrice(19.9)
                .setHotels(hotels)
                .setSales(sales)
                .setTags(tags);

        assertEquals(16L,product.getId());
        assertEquals(19.9, product.getPrice());
        assertEquals(tags,product.getTags());
        assertEquals(hotels,product.getHotels());
        assertEquals(sales,product.getSales());
    }

}
