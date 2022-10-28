package com.acme.hotel_world_api.reservation.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.acme.hotel_world_api.reservation.domain.model.Reservation;
import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.system.domain.model.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class reservationTest {
    Reservation reservation;

    @Test
    @DisplayName("Valid that reservation")
    public void reservation(){
        reservation = new Reservation();
        Date date = new Date();
        Guest guest = new Guest();
        Room room = new Room();
        reservation.setId(2L)
                .setDate(date)
                .setGuest(guest)
                .setRoom(room);

        assertEquals(2L, reservation.getId());
        assertEquals(date, reservation.getDate());
        assertEquals(guest, reservation.getGuest());
        assertEquals(room, reservation.getRoom());


    }

}
