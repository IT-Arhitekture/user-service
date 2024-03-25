package com.itarhitekture.rabbitmq;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageProducer {

    @Inject
    @Channel("user-data-outgoing-channel")
    Emitter<String> emitter;

    public void sendMessage(String message) {
        emitter.send(message);
    }
}