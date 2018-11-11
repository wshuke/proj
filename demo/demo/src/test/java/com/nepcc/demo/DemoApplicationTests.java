package com.nepcc.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nepcc.demo.dao.*;
import com.nepcc.demo.entity.Device;
import com.nepcc.demo.entity.User;
import com.nepcc.demo.service.FaultLogService.QueryFaultLogService;
import com.nepcc.demo.service.deviceServices.HistoryDataService;
import com.nepcc.demo.service.deviceServices.QueryDeviceService;
import com.nepcc.demo.service.deviceServices.UploadDataService;
import com.nepcc.demo.service.userServices.QueryUserService;
import com.nepcc.demo.service.userServices.UserRegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    UserGroupDao userGroupDao;

    @Test
    public void contextLoads(){
        User user = new User();
        user.setCount("nepcc333");
        user.setName("ne");
        user.setPassword("123456");
        user.setUserDescription("no");
        user.setContactInformation("1111");
        user.setPrivilegeValue(3);

    }

/*    @Autowired
    HistoryDataService historyDataService;
    @Test
    public void hdTest(){
        String str = "{\"date1\":\"2018-11-06 16:10:00\",\"date2\":\"2018-11-06 16:40:00\"}";

        try {
            System.out.println("----->\n" + historyDataService.getHistoryDataBetweenTimeByDeviceIdWithInterval(str, 1, 5));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
*/

/*
    @Autowired
    UploadDataService uploadDataService;
    @Test
    public void upTest(){
        String str = "{\"pwd\":\"123456\", \"fTId\":1, \"fV\":410.1}";
        System.out.println(uploadDataService.saveOneDataOfDevice(1,str));
    }
*/

/*
    @Autowired
    QueryDeviceService queryDeviceService;
    @Test
    public void testJsonDevice(){
        Page<Device> devicePage = queryDeviceService.getDevicesByCondition("{\"page\":0, \"pageSize\":5}");
        Map<String, Object> response = new HashMap<>();

        response.put("responseData", JSON.toJSONString(devicePage.getContent(), SerializerFeature.DisableCircularReferenceDetect));          //返回数据JSON
        response.put("pagesNum", devicePage.getTotalPages());           //共多少页
        response.put("pageDataNum", devicePage.getNumberOfElements());  //当前页多少数据
        response.put("totalDataNum", devicePage.getTotalElements());    //共有多少数据

        String json = JSON.toJSONString(response);
        System.out.println("JSON--->  " + json);

        response = JSON.parseObject(json);
        String dataJson =(String) response.get("responseData");
        System.out.println("Object--->  " + JSON.parseArray(dataJson).toJavaList(Device.class));
    }
    */


/*    @Autowired
    QueryFaultLogService queryFaultLogService;
    @Test
    public void faultNotClearTest(){
        System.out.println("result--->"  + queryFaultLogService.getFaultLogByisClear(false, 1));
    }
    */
}
