package com.nepcc.demo.dao;

import com.nepcc.demo.entity.*;
import com.nepcc.demo.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

@Repository
public class FaultLogSpecDao {

    @Autowired
    FaultLogDao faultLogDao;
    @Autowired
    GeneralService generalService;

    public Page<FaultLog> findByCondition(Integer page, Integer pageSize, List<Map<String, Object>> sortBy, Map<String, Object> andBy, Map<String, Object> betweenBy){
        //筛选
        Specification<FaultLog> spec = new Specification<FaultLog>() {
            @Override
            public Predicate toPredicate(Root<FaultLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                criteriaQuery.where(generalService.getAllAndPredicates(root, criteriaBuilder, andBy, null, betweenBy));

                return null;
            }
        };

        //排序
        Sort sort = Sort.by(generalService.getAllSortOrders(sortBy));

        return faultLogDao.findAll(spec, PageRequest.of(page, pageSize, sort));
    }

}
