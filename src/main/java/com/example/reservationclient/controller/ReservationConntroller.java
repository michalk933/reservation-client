package com.example.reservationclient.controller;


import com.example.reservationclient.service.ReservationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationConntroller {

    private final ReservationService reservationService;

    @HystrixCommand
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public void addReservation() {
        reservationService.addReservation("NOWY" + new Random().nextInt(100));
    }

    @HystrixCommand(fallbackMethod = "getReservationNamesFallback")
    @RequestMapping(method = RequestMethod.GET, value = "/name")
    public Collection<String> getReservationNames() {
        return reservationService.getReservationNames();
    }

    public Collection<String> getReservationNamesFallback() {
        return new ArrayList<>();
    }

}
