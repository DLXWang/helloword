package com.test.mysql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.mysql.model.Student;

public interface IStudentService extends IService<Student> {
   void testSaveOrUpdate(Student student);
}
