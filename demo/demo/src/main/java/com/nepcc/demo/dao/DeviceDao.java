package com.nepcc.demo.dao;

import com.nepcc.demo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceDao extends JpaRepository<Device, Integer>, JpaSpecificationExecutor<Device> {

    Device save(Device device);

    long count();

    //@Query(value = "select * from devices d where d.device_id = ?1", nativeQuery = true)
    Device findDeviceById(Integer id);

    void deleteById(Integer id);

    List<Device> findAll();

    @Query(value = "select device_description from devices d where d.device_id = ?1", nativeQuery = true)
    String findDeviceDescritptionById(Integer id);

    @Query(value = "select * from devices d where d.user_id = ?1", nativeQuery = true)
    List<Device> findAllByUserId(Integer id);

    //根据设备的状态来查询各区、各类型设备数目
    @Query(value = "select count(device_id) from devices where device_type_id=?1 and urban_area_id=?2 and device_state_id=?3", nativeQuery = true)
    Long countDevicesbyState(Integer typeId, Integer areaId, Integer stateId);
}
