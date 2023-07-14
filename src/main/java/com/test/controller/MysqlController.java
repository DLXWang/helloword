package com.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.test.mysql.dict.StudentType;
import com.test.mysql.model.Student;
import com.test.mysql.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class MysqlController {
    private final IStudentService studentService;

    @GetMapping("/test-mysql-save")
    public void testSave(@RequestParam Long id, @RequestParam String name) {
        if (id > 0) {
            Student student = Student.builder()
                    .id(id)
                    .name(name)
                    .age(18)
                    .studentType(StudentType.MALE)
                    .build();
            studentService.testSaveOrUpdate(student);

        } else {
            Student student = Student.builder()
                    .name(name)
                    .age(18)
                    .studentType(StudentType.FEMALE)
                    .build();
            studentService.testSaveOrUpdate(student);
        }
    }

    @GetMapping("/get")
    public void testQuery() {
        List<Student> list = studentService.list();
        System.out.println(list);
    }

    @GetMapping("/listName")
    public List<Student> testQueryLam(@RequestParam String name) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getName, name);
        return studentService.list(lambdaQueryWrapper);
    }
}
