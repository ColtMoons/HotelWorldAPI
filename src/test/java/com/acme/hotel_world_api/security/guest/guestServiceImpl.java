package com.acme.hotel_world_api.security.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.security.domain.repository.GuestRepository;
import com.acme.hotel_world_api.security.service.GuestServiceImpl;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class guestServiceImpl {
    @Spy
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestServiceImpl guestServiceImpl;

    Guest guest;
    
    @BeforeEach
    public void setUp(){
        guest = new Guest();

        guest.setId(12L)
        .setName("Pedro")
        .setPassword("password")
        .setUsername("hey");
    }


    @Test
    @DisplayName("When Find by Id Then Return Guest")
    public void whenFindbyIdThenReturnGuest(){
        when(guestRepository.findById(12L)).thenReturn(Optional.of(guest));

        Guest response = guestServiceImpl.getGuestById(12L);

        assertEquals(12L, response.getId());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist")
    public void WhenFindByIdButNotExist(){
        when(guestRepository.findById(12L)).thenReturn(Optional.of(guest));
        String message = "Resource Guest not found for ID with value 34";

        Throwable exception = catchThrowable(()->{
            guestServiceImpl.getGuestById(34L);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Save A Guest")
    public void WhenSaveAGuest(){
        when(guestRepository.save(guest)).thenReturn(guest);

        Guest response = guestServiceImpl.createGuest(guest);

        assertEquals(12L, response.getId());
    }

    
    @Test
    @DisplayName("When Delete A Guest")
    public void WhenDeleteAGuest(){
        doNothing().when(guestRepository).delete(guest);
        when(guestRepository.findById(12L)).thenReturn(Optional.of(guest));

        HttpStatus response = guestServiceImpl.deleteGuest(12L).getStatusCode();

        assertEquals(HttpStatus.OK, response);
    }

    
    @Test
    @DisplayName("When Delete A Guest But Not Exist")
    public void WhenDeleteAGuestButNotExist(){
        doNothing().when(guestRepository).delete(guest);
        when(guestRepository.findById(12L)).thenReturn(Optional.of(guest));

        String message = "Resource Guest not found for ID with value 14";
        Throwable exception = catchThrowable(()->{
            guestServiceImpl.getGuestById(14L);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }

    
    @Test
    @DisplayName("When Update A Guest")
    public void WhenUpdateAGuest(){
        when(guestRepository.findById(12L)).thenReturn(Optional.of(guest));
        when(guestRepository.save(guest)).thenReturn(guest);

        Guest guestRequest = new Guest();

        guestRequest.setId(34L)
        .setName("Jorge")
        .setPassword("password1")
        .setUsername("heysd");

        Guest response = guestServiceImpl.updateGuest(12L, guestRequest);

        assertEquals("Jorge", response.getName());
    }

    @Test
    @DisplayName("When Update A Hotel But Not Exist")
    public void WhenUpdateAGuestButNotExist(){
        when(guestRepository.findById(12L)).thenReturn(Optional.of(guest));
        when(guestRepository.save(guest)).thenReturn(guest);

        Guest guestRequest = new Guest();

        guestRequest.setId(34L)
        .setName("Jorge")
        .setPassword("password1")
        .setUsername("heysd");

        String message = "Resource Guest not found for ID with value 14";

        Throwable exception = catchThrowable(()->{
            guestServiceImpl.updateGuest(14L, guestRequest);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }
}
