package com.balazsando;

import com.balazsando.model.Order;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class FraudDetectionApplication {
    private static Logger LOG = LoggerFactory.getLogger(FraudDetectionApplication.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "fraud-detection-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9094");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> stream = builder.stream("payments");
        stream
                .mapValues((order) -> {
                    String[] orderSplit = order.split(",");
                    return new Order(orderSplit[0], Integer.parseInt(orderSplit[1]), Float.parseFloat(orderSplit[2]));
                })
                .peek(FraudDetectionApplication::printOnEnter)
                .filter((transaction, order) -> !order.getUserId().isEmpty())
                .filter((transaction, order) -> order.getQuantity() < 1000)
                .filter((transaction, order) -> order.getTotalPrice() < 10000)
                .to("validated-payments");

        Topology topology = builder.build();

        try (KafkaStreams streams = new KafkaStreams(topology, props)) {
            streams.start();
            Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        }
    }

    private static void printOnEnter(String transaction, Order order) {
        LOG.info("\n*********************************************");
        LOG.info(
                "ENTERING stream transaction with ID < {} > of user < {} >, total amount < {} > and nb of items < {} >",
                transaction,
                order.getUserId(),
                order.getTotalPrice(),
                order.getQuantity()
        );
    }
}