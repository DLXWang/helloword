package com.test.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class PushEvent<E> extends ApplicationEvent {
    private final E msg;

    public PushEvent(E source) {
        super(source);
        this.msg = source;
    }
}
