package com.test.event.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithGrade {

    private Long id;
    private String name;
    private Integer age;
    private List<Grade> gradle;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Grade {
        private String subject;
        private Double score;
    }

}

