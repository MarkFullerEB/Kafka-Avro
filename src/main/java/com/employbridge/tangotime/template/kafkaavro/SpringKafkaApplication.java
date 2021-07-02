package com.employbridge.tangotime.template.kafkaavro;

import com.employbridge.tangotime.template.kafkaavro.consumer.Receiver;
import com.employbridge.tangotime.template.kafkaavro.producer.Sender;
import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }
}
