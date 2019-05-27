package com.example.reservationclient.configuration;

import com.example.reservationclient.binder.ReservationBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MessageChannelConfiguration {

    @Bean
    public MessageChannel messageChannel(ReservationBinding reservationBinding) {
        return reservationBinding.reservationOut();
    }

}
