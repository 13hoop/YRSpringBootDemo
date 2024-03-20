package com.yongren.github.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/// 车辆表格
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private String image;

    // 续航里程
    @TableField(value = "cruising_range")
    private int cruisingRange;
    // 续航里程(副)
    @TableField(value = "cruising_range_sub")
    private int subCruisingRange;

    // 蓄电池电量
    private int battery;
    // 动力电池电量
    @TableField(value = "power_battery")
    private int powerBattery;
    // 剩余油量(升)
    @TableField(value = "fuel_remaining")
    private int fuelRemaining;
    // 剩余油量,副
    @TableField(value = "fuel_remaining_sub")
    private int subFuelRemaining;

    // 行驶总里程
    @TableField(value = "total_range")
    private int totalRange;

    // 额外数组, 表中不存在
    @TableField(exist = false)
    private BigDecimal price;
}
