package com.test.event;

/**
 * @author wangxinxing
 */
public interface IEventListener<E> {

    void subscribe(PushEvent<E> event);
}
