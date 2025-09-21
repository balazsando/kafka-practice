package com.balazsando;

import com.balazsando.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static java.lang.Thread.sleep;

@Slf4j
public class Main {
    private static final String TOPIC = "user-tracking";

    public static void main(String[] args) throws InterruptedException {
        EventGenerator  eventGenerator = new EventGenerator();

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        try(Producer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 10; i++) {
                log.info("Generating event #{}", i);

                Event event = eventGenerator.generateEvent();

                String key = extractKey(event);
                String value = extractValue(event);

                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, value);

                log.info("Producing to Kafka the record: {}:{}", key, value);
                producer.send(record);

                sleep(1000);
            }
        }
    }

    private static String extractKey(Event event) {
        return event.getUser().getUserId().toString();
    }

    private static String extractValue(Event event) {
        return String.format("%s,%s,%s", event.getProduct().getType(), event.getProduct().getColor(), event.getProduct().getDesignType());
    }
}