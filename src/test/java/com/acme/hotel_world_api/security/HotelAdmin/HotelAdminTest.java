package com.acme.hotel_world_api.security.hotelAdmin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.acme.hotel_world_api.system.domain.model.Hotel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelAdminTest {
    HotelAdmin hotelAdmin;

    @Test
    @DisplayName("Valid That Hotel Admin")
    public void hotelAdmin(){
        Hotel hotel = new Hotel();
        hotel.setId(13L);
        hotelAdmin = new HotelAdmin();
        hotelAdmin.setId(12L)
        .setName("Pedro")
        .setPassword("password")
        .setPhone(666666666L)
        .setUsername("TuAdmin")
        .setHotel(hotel);

        assertEquals(12L, hotelAdmin.getId());
        assertEquals("Pedro", hotelAdmin.getName());
        assertEquals("password", hotelAdmin.getPassword());
        assertEquals(666666666L, hotelAdmin.getPhone());
        assertEquals("TuAdmin", hotelAdmin.getUsername());
        assertEquals(hotel, hotelAdmin.getHotel());
        assertEquals(13L, hotelAdmin.getHotel().getId());
    }
}
