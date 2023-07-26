package com.test.event.service;

import com.test.event.PushEvent;
import com.test.event.PushEvent2;
import com.test.event.model.Consumer;
import com.test.event.model.KafkaListener;
import com.test.event.model.StudentWithGrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class EventListenerService {


    @PostConstruct
    public void init() {
        Consumer consumer = new Consumer();
        consumer.setListener(this::onMessage);
    }


    @EventListener
    @Async
    public void subscribe(PushEvent<StudentWithGrade> event) {
        log.info("<thread is {} >", Thread.currentThread().getName());
        log.info("<receive  event ： {}   >", event.getMsg());
    }

    @EventListener
    @Async
    public void subscribe2(PushEvent2<StudentWithGrade> event2) {
        log.info("<thread2 is {} >", Thread.currentThread().getName());
        log.info("<receive2  event2 ： {} , thread is {} >", event2.getMsg(), Thread.currentThread());
    }

    public void onMessage(String topic, int partition, long offset, int messageType, byte[] body) {

    }

}
