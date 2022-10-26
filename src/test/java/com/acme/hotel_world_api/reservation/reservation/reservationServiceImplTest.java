package com.acme.hotel_world_api.reservation.reservation;

import com.acme.hotel_world_api.reservation.domain.model.Reservation;
import com.acme.hotel_world_api.reservation.domain.repository.ReservationRepository;
import com.acme.hotel_world_api.reservation.service.ReservationServiceImpl;
import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class reservationServiceImplTest {
    @Spy
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;



    Reservation reservation;

    @BeforeEach
    public void setUp(){
        reservation = new Reservation();
        reservation.setId(2L)
                .setDate(new Date("2022-05-04"))
                .setGuest(new Guest())
                .setRoom(new Room());
    }

    @Test
    @DisplayName("When Find by Id Then Return Hotel")
    public void WhenFindByReservationIdThenReturnReservation() {
        when(reservationRepository.findById(2L)).thenReturn(Optional.of(reservation));

        Reservation response = reservationServiceImpl.getReservationById(2L);

        assertEquals(2L, response.getId());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist")
    public void WhenFindByIdButNotExist(){
        when(reservationRepository.findById(2L)).thenReturn(Optional.of(reservation));
        String message = "Resource Reservation not found for ID with value 21";

        Throwable exception = catchThrowable(()->{
            reservationServiceImpl.getReservationById(21L);
        });

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }


}