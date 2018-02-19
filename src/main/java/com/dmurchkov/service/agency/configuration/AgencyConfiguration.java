package com.dmurchkov.service.agency.configuration;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.aspect.AgencyServiceAspect;
import com.dmurchkov.service.agency.kafka.AgencyKafkaProducer;
import com.dmurchkov.service.agency.persistence.Storage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AgencyConfiguration {

    @Value("${bootstrap.servers.config}")
    private String bootstrapServersConfig;

    @Bean
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public AgencyKafkaProducer agencyKafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
                                                   @Value("${agency.topic.name}") String topicName) {
        return new AgencyKafkaProducer(kafkaTemplate, topicName);
    }

    @Bean
    public AgencyService agencyService(Storage storage, AgencyKafkaProducer agencyKafkaProducer) {
        return new AgencyService(storage, agencyKafkaProducer);
    }

    @Bean
    public AgencyServiceAspect agencyServiceAspect() {
        return new AgencyServiceAspect();
    }
}