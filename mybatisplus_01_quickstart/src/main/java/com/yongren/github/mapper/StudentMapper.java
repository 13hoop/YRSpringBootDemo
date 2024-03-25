package com.yongren.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yongren.github.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
//public interface StudentMapper {
//    List<Student> queryAll();
//}

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    // 可以定义额外的方法，这些方法需要在 XML 文件中实现
    List<Student> queryAll();

    Student findById(Integer id);
}