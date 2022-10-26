package com.acme.hotel_world_api.message.chat;


import com.acme.hotel_world_api.message.domain.model.Chat;
import com.acme.hotel_world_api.message.domain.repository.ChatRepository;
import com.acme.hotel_world_api.message.service.ChatServiceImpl;
import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class chatServiceImplTest {

    @Spy
    private ChatRepository chatRepository;

    @InjectMocks
    private ChatServiceImpl chatServiceImpl;

    Chat chat;

    @BeforeEach
    public void setUp(){
        chat = new Chat();
        chat.setId(3L)
                .setAdmin(new HotelAdmin())
                .setGuest(new Guest());
    }


    @Test
    @DisplayName("When Find by Id Then Return Chat")
    public void WhenFindbyIdThenReturnHotel(){
        when(chatRepository.findById(3L)).thenReturn(Optional.of(chat));

        Chat response = chatServiceImpl.getChatById(3L);

        assertEquals(13L, response.getId());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist")
    public void WhenFindByIdButNotExist(){
        when(chatRepository.findById(3L)).thenReturn(Optional.of(chat));
        String message = "Resource Hotel not found for ID with value 35";

        Throwable exception = catchThrowable(()->{
            chatServiceImpl.getChatById(35L);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }
}
