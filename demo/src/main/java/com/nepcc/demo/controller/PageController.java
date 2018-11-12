package com.nepcc.demo.controller;

import com.nepcc.demo.entity.User;
import com.nepcc.demo.service.userServices.UserLoginService;
import com.nepcc.demo.service.userServices.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    UserLoginService loginService;

    /**
     * 登录界面的表单处理，根据帐号count查找对应的user，匹配user的password后，跳转到主页面并返回部分user信息
     * @param count  帐号
     * @param password  密码
     *
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userCertification(@RequestParam("count") String count, @RequestParam("password") String password,
                                    Map<String, Object> map, HttpSession session){

        User existUser = loginService.passwordCertification(count, password);
        if(existUser != null){
            session.setAttribute("loginCount", existUser.getCount());
            session.setAttribute("userId", existUser.getId());
            session.setMaxInactiveInterval(600);//600s无操作则要重新登录

            logger.info("id-"+existUser.getId()+" count:"+existUser.getCount()+" name:"+existUser.getName()+" --login.");

            return "redirect:/index.html";
        }
        else {
            String msg = "帐号或密码错误！";
            map.put("msg",msg);
            return "login.html";
        }
    }
    @ResponseBody
    @RequestMapping(value = "/mp/userLogin", method = RequestMethod.POST)
    public Map<String, Object> mpUserCertification(@RequestParam("count") String count, @RequestParam("password") String password,
                                   HttpSession session){
        Map<String, Object> map = new HashMap<>();

        User existUser = loginService.passwordCertification(count, password);
        if(existUser != null){
            session.setAttribute("loginCount", existUser.getCount());
            session.setAttribute("userId", existUser.getId());
            session.setMaxInactiveInterval(24*60*60);//1天无操作则要重新登录

            map.put("msg","OK");
            logger.info("id-"+existUser.getId()+" count:"+existUser.getCount()+" name:"+existUser.getName()+" --login on phone.");
        }
        else {
            map.put("msg","FAIL");
            map.put("error","帐号或密码错误");
        }
        return map;
    }

    /**
     * 用户退出，session失效
     * @param session  会话
     */
    @ResponseBody
    @RequestMapping("/userExit")
    public String userExit(HttpSession session){
        session.invalidate();
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/mp/userExit")
    public String mpUserExit(HttpSession session){
        session.invalidate();
        return "OK";
    }


    @Autowired
    UserRegistrationService userRegistrationService;
    /**
     * 用户注册，需要判断用户count是否已经存在，同时需要对提交的数据进行处理，判断数据是否符合要求
     */
    @RequestMapping(value = "/newUserRegistration", method = RequestMethod.POST)
    public String newUserRegistration(HttpServletRequest request, Map<String, Object> map){

        User user = new User();

        userRegistrationService.setUserproperties(user, request);   //将表单数据注入User对象

        //检查邀请码
        if(!request.getParameter("key").equals("nepcc2018")){
            map.put("msg","邀请码不正确！请与管理员联系");
            map.put("user",user);
        }
        //判断帐户count是否存在，不存在则可以成功注册，否则返回注册页面
        else if(userRegistrationService.userCountNotExist(request.getParameter("count"))){

            userRegistrationService.saveUser(user);                     //成功注册
            map.put("name",user.getName());
            map.put("count",user.getCount());
            return "registrationSuccess.html";
        }
        else {
            map.put("msg", "此帐号已存在！请使用其他帐号注册..");
            map.put("user",user);
        }
        return "registry.html";
    }



    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello,Springboot!";
    }


}
