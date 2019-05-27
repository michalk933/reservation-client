package com.example.reservationclient.connector;

import com.example.reservationclient.request.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ReservationConnector {

    private final RestTemplate restTemplate;

    public ResponseEntity<Resources<Reservation>> getReservationNames() {

        ParameterizedTypeReference<Resources<Reservation>> responseType =
                new ParameterizedTypeReference<Resources<Reservation>>() {
                };

        return this.restTemplate.exchange(
                "http://localhost:8000/reservations",
                HttpMethod.GET,
                null,
                responseType
        );
    }


}
