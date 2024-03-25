package com.yongren.github.controller;


import com.alibaba.fastjson.JSON;
import com.yongren.github.dao.VehicleDao;
import com.yongren.github.domain.ResposeEntity;
import com.yongren.github.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class BaseVehicleController {

    @Autowired
    private VehicleDao vehicleDao;

    @RequestMapping("/list")
    public String findAll() {

        List<Vehicle> vehicles = vehicleDao.selectList(null);
        ResposeEntity rsp = new ResposeEntity();
        rsp.setCode(0);
        rsp.setMessage("success");
        rsp.setData(vehicles);
        return JSON.toJSONString(rsp);
    }

    // 这里如何让参数自动装填 即 JSON.parseObject(value, Vehicle.class) ?
    // 直接返回ResposeEntity目前不行, 如何处理 ?
    @RequestMapping("/addObject")
    public ResposeEntity addVehicleObject(@RequestBody Vehicle vehicle) {
//        Vehicle newVehicle = new Vehicle();
//        newVehicle = vehicle;
        System.out.println(" --> " + vehicle);
//        vehicleDao.insert(vehicle);

        ResposeEntity rsp = new ResposeEntity();
        rsp.setCode(0);
        rsp.setMessage("success");
        rsp.setData(vehicle);
        return rsp;
    }

    @RequestMapping("/add")
    public String addVehicle(@RequestBody String value) {
        Vehicle vehicle = JSON.parseObject(value, Vehicle.class);
        System.out.println(" --> " + vehicle);
        vehicleDao.insert(vehicle);

        ResposeEntity rsp = new ResposeEntity();
        rsp.setCode(0);
        rsp.setMessage("success");
        rsp.setData(vehicle);
        return JSON.toJSONString(rsp);
    }

    @GetMapping("/sts")
    public String querySts() {
        ResposeEntity rsp = new ResposeEntity();
        rsp.setCode(0);
        rsp.setMessage("success");
        rsp.setData("done!");
        return JSON.toJSONString(rsp);
    }

}
