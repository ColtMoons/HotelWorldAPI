package com.acme.hotel_world_api.sales.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.model.Tag;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TagTest {
    Tag tag;

    @Test
    @DisplayName("Valid That Hotel")
    public void hotel(){
        tag = new Tag();
        List<Product> products = new ArrayList<Product>();

        tag.setId(12L)
        .setName("hola")
        .setProducts(products);

        assertEquals(12L, tag.getId());
        assertEquals("hola", tag.getName());
        assertEquals(products, tag.getProducts());

    }
}
