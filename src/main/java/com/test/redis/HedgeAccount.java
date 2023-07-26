package com.test.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class HedgeAccount {

    private Long id;

    private String name;

    private String comment;

    /**
     * 是否生效
     */

    private Boolean enabled;

    private String creatorId;
    private String updaterId;
    private LocalDateTime createdTime;
    private Date updatedTime;

}
