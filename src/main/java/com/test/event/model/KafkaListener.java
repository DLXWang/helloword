package com.test.event.model;


public interface KafkaListener {
    void onMessage(String topic, int partition, long offset, int messageType, byte[] body);

    default void onIdle() {
    }
}