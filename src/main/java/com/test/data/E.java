package com.test.data;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class E implements I{
    private final List<I> list;
    private Map<String,List<I>> topicDispatcherMap;

    @PostConstruct
    public void init() {
        topicDispatcherMap = new HashMap<>(list.size());
        list.forEach(listener -> {
            TopicSupport annotation = listener.getClass().getAnnotation(TopicSupport.class);
            if (Objects.nonNull(annotation)) {
                topicDispatcherMap.computeIfAbsent(annotation.value(), it -> new ArrayList<>()).add(listener);
            }
        });
        System.out.println(topicDispatcherMap);
    }
}
