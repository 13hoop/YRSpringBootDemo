package com.yongren.github.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yongren.github.domain.Vehicle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleDao extends BaseMapper<Vehicle> {
}
