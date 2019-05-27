package com.example.reservationclient.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


@Value
public class Reservation {

    private String reservationName;

    @JsonCreator
    public Reservation(@JsonProperty("reservationName") String reservationName) {
        this.reservationName = reservationName;
    }

}
