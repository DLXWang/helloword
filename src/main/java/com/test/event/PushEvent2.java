package com.test.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class PushEvent2<E> extends ApplicationEvent {
    private final E msg;

    public PushEvent2(E source) {
        super(source);
        this.msg = source;
    }
}
