package com.yongren.github.service.imp;

import com.yongren.github.domain.Student;
import com.yongren.github.mapper.StudentMapper;
import com.yongren.github.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.queryAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }
}
