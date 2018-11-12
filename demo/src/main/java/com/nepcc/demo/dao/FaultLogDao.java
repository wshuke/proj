package com.nepcc.demo.dao;

import com.nepcc.demo.entity.Device;
import com.nepcc.demo.entity.FaultLog;
import com.nepcc.demo.entity.FaultType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface FaultLogDao extends JpaRepository<FaultLog, Integer>, JpaSpecificationExecutor<FaultLog> {

    FaultLog save(FaultLog faultLog);

    List<FaultLog> findAll();

    //查询故障消除或未消除的日志
    List<FaultLog> findFaultLogsByIsFaultClear(Boolean bool);

    List<FaultLog> findFaultLogsByIsFaultClearAndDevice(Boolean bool, Device device);

    FaultLog findFaultLogByIsFaultClearAndDeviceAndFaultType(Boolean bool, Device device, FaultType faultType);

    FaultLog saveAndFlush(FaultLog faultLog);

    FaultLog findFaultLogById(Integer id);

}
