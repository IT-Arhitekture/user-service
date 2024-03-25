package com.itarhitekture.rabbitmq;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MessageConsumer {

    @Incoming("user-data-incoming-channel")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}