package com.test.controller;


import com.test.event.model.StudentWithGrade;
import com.test.event.service.EventPublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController

@RequiredArgsConstructor
@Api(tags = "event 测试")
public class EventController {

    private final EventPublisherService eventPublisherService;

    @ApiOperation("testEventPublish")
    @GetMapping("/test-event-publish")
    public void testEventPublish(@RequestParam Long id, @RequestParam String name) {
        StudentWithGrade.Grade math = StudentWithGrade.Grade.builder().subject("Math")
                .score(99.1).build();
        StudentWithGrade build = StudentWithGrade.builder()
                .id(id)
                .name(name)
                .gradle(Collections.singletonList(math)).build();
        eventPublisherService.publish(build);
        eventPublisherService.publish2(build);
    }

}
