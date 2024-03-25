package com.yongren.github.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;

    private String name;

    private String no;
}
