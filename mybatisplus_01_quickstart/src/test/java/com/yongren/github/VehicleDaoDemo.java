package com.yongren.github;

import com.yongren.github.dao.VehicleDao;
import com.yongren.github.domain.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleDaoDemo {

    @Autowired
    private VehicleDao vehicleDao;

    @Test
    void insert() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("皮卡-炮001");
        vehicle.setImage("https://res.gwm.com.cn/Pick/static/img/shp-performance/1.jpg");
        vehicle.setBattery(1);
        vehicle.setTotalRange(8888);
        vehicleDao.insert(vehicle);

    }
}
