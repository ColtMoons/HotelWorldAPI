package com.acme.hotel_world_api.system.hotel;

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

import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.model.Hotel;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;
import com.acme.hotel_world_api.system.service.HotelServiceImpl;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class hotelServiceImplTest {
    @Spy
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelServiceImpl;

    Hotel hotel;

    @BeforeEach
    public void setUp(){
        hotel = new Hotel();
        hotel.setId(13L)
        .setName("El cielo")
        .setPhone(666666666L)
        .setAddress("Av. Arequipa");
    }

    @Test
    @DisplayName("When Find by Id Then Return Hotel")
    public void WhenFindbyIdThenReturnHotel(){
        when(hotelRepository.findById(13L)).thenReturn(Optional.of(hotel));

        Hotel response = hotelServiceImpl.getHotelById(13L);

        assertEquals(13L, response.getId());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist")
    public void WhenFindByIdButNotExist(){
        when(hotelRepository.findById(13L)).thenReturn(Optional.of(hotel));
        String message = "Resource Hotel not found for ID with value 34";

        Throwable exception = catchThrowable(()->{
            hotelServiceImpl.getHotelById(34L);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Save A Hotel")
    public void WhenSaveAHotel(){
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel response = hotelServiceImpl.createHotel(hotel);

        assertEquals(13L, response.getId());
    }

    @Test
    @DisplayName("When Delete A Hotel")
    public void WhenDeleteAHotel(){
        doNothing().when(hotelRepository).delete(hotel);
        when(hotelRepository.findById(13L)).thenReturn(Optional.of(hotel));

        HttpStatus response = hotelServiceImpl.deleteHotel(13L).getStatusCode();

        assertEquals(HttpStatus.OK, response);
    }

    @Test
    @DisplayName("When Delete A Hotel But Not Exist")
    public void WhenDeleteAHotelButNotExist(){
        doNothing().when(hotelRepository).delete(hotel);
        when(hotelRepository.findById(13L)).thenReturn(Optional.of(hotel));

        String message = "Resource Hotel not found for ID with value 14";
        Throwable exception = catchThrowable(()->{
            hotelServiceImpl.getHotelById(14L);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Update A Hotel")
    public void WhenUpdateAHotel(){
        when(hotelRepository.findById(13L)).thenReturn(Optional.of(hotel));
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel hotelRequest = new Hotel();

        hotelRequest.setId(24L).setName("cuquis")
        .setPhone(999999999L).setAddress("luriganchito");

        Hotel response = hotelServiceImpl.updateHotel(13L, hotelRequest);

        assertEquals("cuquis", response.getName());
    }
    
    @Test
    @DisplayName("When Update A Hotel But Not Exist")
    public void WhenUpdateAHotelButNotExist(){
        when(hotelRepository.findById(13L)).thenReturn(Optional.of(hotel));
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel hotelRequest = new Hotel();

        hotelRequest.setId(24L).setName("cuquis")
        .setPhone(999999999L).setAddress("luriganchito");

        String message = "Resource Hotel not found for ID with value 14";

        Throwable exception = catchThrowable(()->{
            hotelServiceImpl.updateHotel(14L, hotelRequest);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }
}
