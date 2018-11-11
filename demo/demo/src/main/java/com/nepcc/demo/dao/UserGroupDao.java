package com.nepcc.demo.dao;

import com.nepcc.demo.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupDao extends CrudRepository<UserGroup, Integer> {

    UserGroup findUserGroupById(Integer id);
}
