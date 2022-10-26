package com.acme.hotel_world_api.message.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.acme.hotel_world_api.message.domain.model.Chat;
import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class chatTest {
    Chat chat;

    @Test
    @DisplayName("Valid That Chat")
    public void chat(){
        chat = new Chat();
        chat.setId(3L)
                .setAdmin(new HotelAdmin())
                .setGuest(new Guest());

        assertEquals(3L, chat.getId());
        assertEquals(new HotelAdmin(), chat.getAdmin());
        assertEquals(new Guest(), chat.getGuest());
    }

}
