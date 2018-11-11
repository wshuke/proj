package com.nepcc.demo.service.userServices;

import com.nepcc.demo.dao.UserDao;
import com.nepcc.demo.dao.UserGroupDao;
import com.nepcc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserRegistrationService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserGroupDao userGroupDao;

    public void saveUser(User user){

        userDao.save(user);
    }

    public User updateUser(User user){
        return userDao.saveAndFlush(user);
    }

    public Boolean userCountNotExist(String count){
        if(userDao.findUserByCount(count) == null){
            return true;
        }
        else return false;
    }

    public void setUserproperties(User user, HttpServletRequest request){
        if(user.getId() == null){  //表明是新用户注册
            user.setCount(request.getParameter("count"));
            user.setPassword(request.getParameter("password"));
            user.setPrivilegeValue(3);
            user.setUserGroup(userGroupDao.findUserGroupById(3));     //visitors
        }   //不是新用户注册，就是只修改个人信息
        user.setName(request.getParameter("name"));
        user.setUserDescription(request.getParameter("userDescription"));
        user.setContactInformation(request.getParameter("contactInformation"));

    }


}
