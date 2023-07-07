package com.test.controller;


import com.test.mysql.dict.StudentType;
import com.test.mysql.model.Student;
import com.test.mysql.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                    .studentType(StudentType.MALE)
                    .build();
            studentService.testSaveOrUpdate(student);
        }
    }
}