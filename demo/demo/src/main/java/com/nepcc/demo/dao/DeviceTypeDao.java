package com.nepcc.demo.dao;

import com.nepcc.demo.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeDao  extends JpaRepository<DeviceType, Integer> {

    DeviceType findDeviceTypeById(Integer id);

}
