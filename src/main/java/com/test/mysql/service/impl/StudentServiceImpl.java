package com.test.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.mysql.mapper.StudentMapper;
import com.test.mysql.model.Student;
import com.test.mysql.service.IStudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Override
    public void testSaveOrUpdate(Student student) {
        boolean b = saveOrUpdate(student);
        System.out.println(student);
        System.out.println(b);
    }
}
