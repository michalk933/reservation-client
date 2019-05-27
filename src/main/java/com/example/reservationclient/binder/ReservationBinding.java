package com.example.reservationclient.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ReservationBinding {

    String RESERVATION_OUT = "reservations";

    @Output(RESERVATION_OUT)
    MessageChannel reservationOut();

}
