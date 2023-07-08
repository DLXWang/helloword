package com.test.controller;


import com.test.event.model.StudentWithGrade;
import com.test.event.service.EventPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController

@RequiredArgsConstructor
public class EventController {

    private final EventPublisherService eventPublisherService;

    @GetMapping("/test-event-publish")
    public void testEventPublish(@RequestParam Long id, @RequestParam String name) {
        StudentWithGrade.Grade math = StudentWithGrade.Grade.builder().subject("Math")
                .score(99.1).build();
        StudentWithGrade build = StudentWithGrade.builder()
                .id(id)
                .name(name)
                .gradle(Collections.singletonList(math)).build();
        eventPublisherService.publish(build);
    }

}
