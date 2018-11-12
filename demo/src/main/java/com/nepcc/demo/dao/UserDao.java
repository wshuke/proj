package com.nepcc.demo.dao;

import com.nepcc.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    //通过帐号count查询用户
    //@Query(value = "select * from users u where u.count = ?1", nativeQuery = true)
    User findUserByCount(String count);


    //通过帐号count获取该用户的ID
    @Query(value = "select user_id from users u where u.count = ?1", nativeQuery = true)
    Integer findUserIdByCount(String count);


//    @Modifying
//    @Transactional

    //保存一个用户信息
    User save(User user);

    //查询所有用户信息
    //@Query(value = "select * from users", nativeQuery = true)
    List<User> findAll();

    User findUserById(Integer id);

}
