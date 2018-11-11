package com.nepcc.demo.dao;

import com.nepcc.demo.entity.Device;
import com.nepcc.demo.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DeviceSpecDao {

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    GeneralService generalService;

    public Page<Device> findByConditions(String search, List<Map<String, Object>> sortBy, Map<String, Object> equalBy, Map<String, Object> betweenBy, Integer page, Integer pageSize){

        //筛选
        Specification<Device> spec = new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                criteriaQuery.where(generalService.getAllAndPredicates(root, criteriaBuilder, equalBy, search, betweenBy));
                return null;
            }
        };
        //排序
        Sort sort = Sort.by(generalService.getAllSortOrders(sortBy));

        return deviceDao.findAll(spec, PageRequest.of(page, pageSize, sort));
    }
}
