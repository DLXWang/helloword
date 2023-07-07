package com.test.mysql.model;


import com.baomidou.mybatisplus.annotation.*;
import com.test.mysql.dict.StudentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("test_student")
public class Student {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private StudentType studentType;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;
}

/**
 * CREATE TABLE `test_student`
 * (
 *     `id`           bigint NOT NULL AUTO_INCREMENT,
 *     `age`         bigint(20) NOT NULL,
 *     `name`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '姓名',
 *     `student_type`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '类型',
 *     `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *     `updated_time`     TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 *      PRIMARY KEY (`id`)
 * );
 */
