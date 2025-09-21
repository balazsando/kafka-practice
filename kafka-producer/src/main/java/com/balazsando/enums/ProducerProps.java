package com.balazsando.enums;

public enum ProducerProps {
    BOOTSTRAP_SERVERS("bootstrap.servers"),
    KEY_SERIALIZER("key.serializer"),
    VALUE_SERIALIZER("value.serializer");

    public final String value;

    ProducerProps(String value) {
        this.value = value;
    }
}
