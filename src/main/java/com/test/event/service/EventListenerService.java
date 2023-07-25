package com.test.event.service;

import com.test.event.IEventListener;
import com.test.event.PushEvent;
import com.test.event.PushEvent2;
import com.test.event.model.StudentWithGrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventListenerService   {



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
}
