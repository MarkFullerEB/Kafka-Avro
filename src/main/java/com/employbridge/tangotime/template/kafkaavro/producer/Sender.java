package com.employbridge.tangotime.template.kafkaavro.producer;

import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topic.avro}")
    private String avroTopic;

    @Autowired
    private KafkaProducer<String, User> producer;

    public void send(User user) {
        producer.send(new ProducerRecord<String, User>(avroTopic, user.getName().toString(), user));

    }
}
