package com.nepcc.demo.dao;

import com.nepcc.demo.entity.User;
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
public class UserSpecDao {

    @Autowired
    UserDao userDao;

    @Autowired
    GeneralService generalService;

    public Page<User> findByConditions(String search, List<Map<String, Object>> sortBy, Map<String, Object> equalBy, Map<String, Object> betweenBy, Integer page, Integer pageSize){

        //筛选
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(generalService.getAllAndPredicates(root, criteriaBuilder, equalBy, search, betweenBy));
                return null;
            }
        };

        //排序
        Sort sort = Sort.by(generalService.getAllSortOrders(sortBy));

        return userDao.findAll(spec, PageRequest.of(page, pageSize, sort));
    }

}
