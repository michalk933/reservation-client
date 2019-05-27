package com.example.reservationclient.service;

import java.util.Collection;

public interface ReservationService {

    void addReservation(String nameReservation);

    Collection<String> getReservationNames();

}
