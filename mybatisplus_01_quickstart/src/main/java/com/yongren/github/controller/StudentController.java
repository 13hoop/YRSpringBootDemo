package com.yongren.github.controller;

import com.yongren.github.domain.Student;
import com.yongren.github.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public String queryStudentBy(@PathVariable("id") Integer id) {
        System.out.println(" [user] ===> find by id = " + id);
        Student ss = studentService.findById(id);
        return " --> " + ss.toString();
    }

    @GetMapping("/list")
    public String queryAll() {
        List<Student> ss = studentService.findAll();
        return " --> " + ss.toString();
    }
}
