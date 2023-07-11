package com.test.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class HedgeAccount {
    @Id
    private Long id;
    private String name;
    private String comment;
    private Boolean enabled;

    private String creatorId;
    private String updaterId;
    private Long createdTime;
    private Long updatedTime;

}
