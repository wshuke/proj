package com.nepcc.demo.dao;

import com.nepcc.demo.entity.FaultType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultTypeDao extends JpaRepository<FaultType, Integer> {

    FaultType findFaultTypeById(Integer id);
}
