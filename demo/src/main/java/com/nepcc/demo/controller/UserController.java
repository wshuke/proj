package com.nepcc.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nepcc.demo.entity.User;
import com.nepcc.demo.service.userServices.QueryUserService;
import com.nepcc.demo.service.userServices.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    QueryUserService queryUserService;

    /**
     * 获取一个用户的信息
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable("id") Integer id, HttpSession session){

        User user = queryUserService.getUserById(id);
        if(user == null)
            return null;
        user.setPassword(null);     //隐藏password

        if(queryUserService.getUserById((Integer) session.getAttribute("userId")).getUserGroup().getId()<3) {
            //userGroup.Id < 3才是管理员，如果不是管理员，则只能获取自己的信息
            return user;
        }
        else if(session.getAttribute("userId").equals(user.getId())){   //通过Session中的id来判断是不是本人发送的请求
            return user;
        }
        return null;
    }

    /**
     * 浏览器获取当前用户的Id,未登录返回0
     * @return
     */
    @RequestMapping(value = "/getThisUser", method = RequestMethod.GET)
    public Integer getThisUser(HttpSession session){
        Integer thisId = (Integer) session.getAttribute("userId");  //从session中获取当前用户的ID
        User user;

        if(thisId == null)
            return 0;       //表明用户未登录，不可以取得ID，返回0
        else
        {
            user = queryUserService.getUserById(thisId);
            if(user!=null)  //用户已登录，获取其ID
                return user.getId();
        }

        return 0;
    }

    /**
     * 根据条件动态查询用户信息列表
     * @param DynamicRequest
     * @param mp 如果有mp参数则为Android端请求，返回的responseData为json字符串
     * @return
     */
    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public Map<String, Object> getUsers(@RequestParam(value = "DynamicRequest") String DynamicRequest, @RequestParam(value = "mp", required = false) Integer mp, HttpSession session){
        if(queryUserService.getUserById( (Integer) session.getAttribute("userId") ).getUserGroup().getId() > 2) // < 3为管理员，才有权限调用该查询
            return null;

        Map<String, Object> response = new HashMap<>();

        try {
            Page<User> userPage = queryUserService.getUsersByCondotions(DynamicRequest);

            if(mp != null)  //判断是不是Android端的请求
                response.put("responseData", JSON.toJSONString(userPage.getContent(), SerializerFeature.DisableCircularReferenceDetect));    //返回数据JSON字符串
            else
                response.put("responseData", userPage.getContent());            //返回数据JSON字符串
            response.put("totalPages", userPage.getTotalPages());           //共多少页
            response.put("pageDataNum", userPage.getNumberOfElements());    //当前页多少数据
            response.put("totalData", userPage.getTotalElements());         //共有多少数据
        } catch (Exception e) {
            e.printStackTrace();
            response.clear();
            response.put("Error",e.getMessage());
        }

        return  response;
    }

    @Autowired
    UserRegistrationService userRegistrationService;

    /**
     * 用户修改个人资料，仅改变name，userDescription，contactInformation三个属性
     * @param id
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/modifyUser/{id}", method = RequestMethod.POST)
    public User modifyUser(@PathVariable("id") Integer id, HttpServletRequest request, HttpSession session){
        try {
            User user = queryUserService.getUserById(id);
            if(user == null)
                return null;
            if(session.getAttribute("userId").equals(user.getId())){
                userRegistrationService.setUserproperties(user, request);
                user = userRegistrationService.updateUser(user);
                user.setPassword(null);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用户修改密码，参数为新密码和旧密码，旧密码正确才能修改新密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param session
     * @return
     */
    @RequestMapping(value = "/modifyPassword/{id}", method = RequestMethod.POST)
    public String modifyPassword(@PathVariable("id") Integer id, @RequestParam("oldPassword") String oldPassword,  @RequestParam("newPassword") String newPassword,HttpSession session){
        try {
            User user = queryUserService.getUserById(id);
            if(session.getAttribute("userId").equals(user.getId())){
                if(oldPassword.equals(user.getPassword())){         //旧密码正确
                    user.setPassword(newPassword);                  //修改新密码
                    userRegistrationService.updateUser(user);
                    return "OK";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL";
    }


    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(){
        return queryUserService.getUserById(2);
    }
}
