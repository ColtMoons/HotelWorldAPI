package com.acme.hotel_world_api.system.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acme.hotel_world_api.system.domain.model.Hotel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class hotelTest {
    Hotel hotel;

    @Test
    @DisplayName("Valid That Hotel")
    public void hotel(){
        hotel = new Hotel();
        hotel.setId(13L)
        .setName("El cielo")
        .setPhone(666666666L)
        .setAddress("Av. Arequipa");

        assertEquals(13L, hotel.getId());
        assertEquals("El cielo", hotel.getName());
        assertEquals(666666666L, hotel.getPhone());
        assertEquals("Av. Arequipa", hotel.getAddress());
    }
}
