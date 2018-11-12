package com.nepcc.demo.service.userServices;

import com.nepcc.demo.dao.UserDao;
import com.nepcc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    @Autowired
    UserDao userDao;

    public User passwordCertification(String count, String password){
        User user = userDao.findUserByCount(count);
        if(user == null){
            return null;
        }
        else if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
