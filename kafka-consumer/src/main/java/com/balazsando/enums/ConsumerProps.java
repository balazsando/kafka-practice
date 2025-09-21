package com.balazsando.enums;

public enum ConsumerProps {
    BOOTSTRAP_SERVERS("bootstrap.servers"),
    KEY_DESERIALIZER("key.deserializer"),
    VALUE_DESERIALIZER("value.deserializer"),
    GROUP_ID("group.id"),
    AUTO_OFFSET_RESET("auto.offset.reset");

    public final String value;

    ConsumerProps(String value) {
        this.value = value;
    }
}
