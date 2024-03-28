package com.yongren.github.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResposeEntity implements Serializable {
    // 数据体
    private Object data;
    // code: 0 表示成功
    private int code;
    // 返回信息: 成功为success, 其他情况代错误信息
    private String message;
}
