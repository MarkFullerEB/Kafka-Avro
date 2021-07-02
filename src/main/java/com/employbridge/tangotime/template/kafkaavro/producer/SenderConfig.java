package com.employbridge.tangotime.template.kafkaavro.producer;

import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableKafka
public class SenderConfig {

    @Value("${kafka.topic.avro}")
    private String PRODUCER_TOPIC;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Properties producerConfigs() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, bootstrapServers);

        return props;
    }

    @Bean
    public KafkaProducer<String, User> producerFactory(){
        KafkaProducer kp = new KafkaProducer<String, User>(producerConfigs());

        // Helps end the program
        Runtime.getRuntime().addShutdownHook(new Thread(kp::close));

       return kp;
    }

}
