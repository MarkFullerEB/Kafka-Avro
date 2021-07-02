package com.employbridge.tangotime.template.kafkaavro.consumer;

import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class Receiver {

    @Autowired
    private KafkaConsumer<String, User> consumer;

    @Bean
    public List<User> consume(){
        ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(1000));
        ArrayList<User> data = new ArrayList<User>();

        for(ConsumerRecord<String, User> record: records) {
            User u = record.value();
            data.add(u);
        }

        return data;
    }
}
