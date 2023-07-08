package com.test.event.service;

import com.test.event.IEventPublisher;
import com.test.event.PushEvent;
import com.test.event.model.StudentWithGrade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventPublisherService implements IEventPublisher<StudentWithGrade> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(StudentWithGrade msg) {
        log.info("<thread is {} >", Thread.currentThread().getName());
        applicationEventPublisher.publishEvent(new PushEvent<>(msg));
    }
}
