package com.test.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.mysql.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
