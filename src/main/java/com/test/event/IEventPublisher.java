package com.test.event;

public interface IEventPublisher<E> {
    void publish(E msg);
}
