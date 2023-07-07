package com.test.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class A {
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
