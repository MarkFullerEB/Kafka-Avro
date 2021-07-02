package com.employbridge.tangotime.template.kafkaavro.consumer;

import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

//    @KafkaListener(topics = "${kafka.topic.avro}")
//    public void listen(@Payload User user) {
//        System.err.println("Received User"); LOGGER.info("received user='{}'", user.toString());
//    }
}
