package com.test.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@TopicSupport(value = "dd")
public class A implements I{
    private  String name ;
    private List<AP> aps;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AP{
        private  String apName ;
    }
}
