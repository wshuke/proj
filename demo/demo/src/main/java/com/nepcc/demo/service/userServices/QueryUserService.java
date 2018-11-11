package com.nepcc.demo.service.userServices;

import com.alibaba.fastjson.JSON;
import com.nepcc.demo.dao.UserDao;
import com.nepcc.demo.dao.UserSpecDao;
import com.nepcc.demo.entity.User;
import com.nepcc.demo.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class QueryUserService {

    @Autowired
    UserDao userDao;

    public List<User> getUserInfoList(){
        List<User> userList = userDao.findAll();
        return userList;
    }

    //通过count得到user的id而不是整个user类对象
    public Integer getUserIdByCount(String count){
        return userDao.findUserIdByCount(count);
    }

    public User getUserById(Integer id){

        return userDao.findUserById(id);
    }

    @Autowired
    UserSpecDao userSpecDao;
    @Autowired
    GeneralService generalService;

    /**
     * 查询user的通用方法，安全起见返回结果中将password置为null
     * @param jsonStr 装载可选查询条件的json数据
     * @return Page<User>
     */
    public Page<User> getUsersByCondotions(String jsonStr){
        Map<String, Object> map = JSON.parseObject(jsonStr);

        //查看分页参数是否存在，不存在则做默认处理
        Integer page = generalService.processQueryPage(map);
        Integer pageSize = generalService.processQueryPageSize(map);

        Page<User> userPage= userSpecDao.findByConditions(  (String) map.get("search"),
                                                            (List<Map<String, Object>>) map.get("sortBy"),
                                                            (Map<String, Object>) map.get("equalBy"),
                                                            (Map<String, Object>) map.get("betweenBy"),
                                                            page, pageSize);
        List<User> list = userPage.getContent();
        Iterator it = list.iterator();          //安全起见返回结果中将password置为null
        while(it.hasNext()){
            ((User)it.next()).setPassword(null);
        }

        return userPage;
    }

}
