package com.balazsando;

import com.balazsando.enums.ConsumerProps;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Main {
    private static final String TOPIC = "user-tracking";
    private static final Object GROUP_ID = "user-tracking-consumer";

    public static void main(String[] args) {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        Properties props = new Properties();
        props.put(ConsumerProps.BOOTSTRAP_SERVERS.value, "localhost:9092,localhost:9094");
        props.put(ConsumerProps.GROUP_ID.value, GROUP_ID);
        props.put(ConsumerProps.AUTO_OFFSET_RESET.value, "earliest");
        props.put(ConsumerProps.KEY_DESERIALIZER.value, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerProps.VALUE_DESERIALIZER.value, "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(TOPIC));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received record: key=%s, value=%s%n", record.key(), record.value());
                    suggestionEngine.processSuggestions(record.key(), record.value());
                }
            }
        }
    }
}