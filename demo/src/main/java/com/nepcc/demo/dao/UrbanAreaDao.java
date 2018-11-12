package com.nepcc.demo.dao;

import com.nepcc.demo.entity.UrbanArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UrbanAreaDao extends JpaRepository<UrbanArea , Integer> {

    UrbanArea findUrbanAreaById(Integer id);

}
