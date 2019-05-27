package com.example.reservationclient.service.impl;

import com.example.reservationclient.connector.ReservationConnector;
import com.example.reservationclient.producer.ReservaionProducer;
import com.example.reservationclient.request.Reservation;
import com.example.reservationclient.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationConnector reservationConnector;
    private final ReservaionProducer reservaionProducer;

    @Override
    public void addReservation(String nameReservation) {
        reservaionProducer.sendReservation(new Reservation(nameReservation));
    }

    @Override
    public Collection<String> getReservationNames() {
        return Objects.requireNonNull(reservationConnector
                .getReservationNames()
                .getBody())
                .getContent()
                .stream()
                .map(Reservation::getReservationName)
                .collect(Collectors.toList());
    }
}
