package com.nepcc.demo.dao;

import com.nepcc.demo.entity.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStateDao extends JpaRepository<DeviceState, Integer> {

    DeviceState findDeviceStateById(Integer id);

}
