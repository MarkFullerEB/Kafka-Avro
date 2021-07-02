package com.employbridge.tangotime.template.kafkaavro.producer;

import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topic.avro}")
    private String avroTopic;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void send(User user) {
        System.err.println("Sending User");
        LOGGER.info("sending user='{}'", user.toString());
        kafkaTemplate.send(avroTopic, user);
    }
}
