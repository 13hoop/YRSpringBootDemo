package com.yongren.github.service;

import com.yongren.github.domain.Student;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface StudentService {
    List<Student> findAll();

    Student findById(Integer id);
}
