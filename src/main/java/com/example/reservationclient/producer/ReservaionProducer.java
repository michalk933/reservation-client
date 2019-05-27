package com.example.reservationclient.producer;

import com.example.reservationclient.exception.KafkaProducerGeneralException;
import com.example.reservationclient.request.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservaionProducer {

    private final MessageChannel messageChannel;

    public void sendReservation(Reservation addReservation) {
        log.info("Add Reserwation: " + addReservation.toString());
        Message<Reservation> build = MessageBuilder
                .withPayload(addReservation)
                .build();
        try {
            this.messageChannel.send(build);
        } catch (Exception e) {
            throw new KafkaProducerGeneralException(String.format("Something wrong during send even to kafka. Reservation: %s", addReservation), e);
        }

    }

}
