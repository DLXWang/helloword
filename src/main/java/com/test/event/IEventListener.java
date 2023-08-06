package com.test.event;

/**
 * @author wangxinxing
 */
public interface IEventListener<E> {

    void subscribe(PushEvent<E> event);

    void subscribe2(PushEvent2<E> event2);
}
