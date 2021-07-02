package com.employbridge.tangotime.template.kafkaavro.consumer;

import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Collections;
import java.util.Properties;

@Configuration
@EnableKafka
public class ReceiverConfig {
    @Value("${kafka.topic.avro}")
    private String PRODUCER_TOPIC;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.schema-registry}")
    private String schemaServer;

    @Bean
    public Properties consumerConfigs() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "user.consumer.sr");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaServer);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        return props;
    }

    @Bean
    public KafkaConsumer<String, User> consumerFactory() {
        KafkaConsumer<String, User> kc = new KafkaConsumer<String, User>(consumerConfigs());

        // makes stopping the application easier
        Runtime.getRuntime().addShutdownHook(new Thread(kc::close));

        kc.subscribe(Collections.singletonList(PRODUCER_TOPIC));

        return kc;
    }


}
