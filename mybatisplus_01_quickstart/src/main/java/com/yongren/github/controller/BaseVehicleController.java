package com.yongren.github.controller;


import com.alibaba.fastjson.JSON;
import com.yongren.github.dao.VehicleDao;
import com.yongren.github.domain.ResposeEntity;
import com.yongren.github.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @RequestMapping(value = "/list2")
    @ResponseBody
    public ResposeEntity findAllEntity() {
        List<Vehicle> vehicles = vehicleDao.selectList(null);
        ResposeEntity rsp = new ResposeEntity();
        rsp.setCode(0);
        rsp.setMessage("success");
        rsp.setData(vehicles);
        return rsp;
    }

    // 01 这里如何让参数自动装填成对象, 即替代 JSON.parseObject(value, Vehicle.class) 和 参数中自动装配为 对象?
    // 02 直接返回 ResposeEntity 目前不行, 如何处理 ?
    // 03 错误处理 ? 拦截器 ?
    // 04 context如何scan到有那些bean的 ? 在classpath目录下具体扫描后加载的过程 ?

//    @RequestMapping("/addObject")
    @PostMapping(value = "/addObject")
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
