package com.test.mysql.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentType {
    MALE(0, "男"),
    FEMALE(1, "女");
    @EnumValue  // 高版本直接用
    private final int value;
    private final String desc;
}
