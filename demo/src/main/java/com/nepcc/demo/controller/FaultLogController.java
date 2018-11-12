package com.nepcc.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nepcc.demo.entity.FaultLog;
import com.nepcc.demo.service.FaultLogService.QueryFaultLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FaultLogController {

    @Autowired
    QueryFaultLogService queryFaultLogService;


    /**
     * 根据条件动态查询故障日志
     * @param DynamicRequest
     * @param mp 如果有mp参数则为Android端请求，返回的responseData为json字符串，同时，mp存在时会把FaultLog的device属性删除仅留下deviceId属性减少数据传输
     * @return
     */
    @RequestMapping(value = "/getFaultLogs", method = RequestMethod.POST)
    public Map<String, Object> getFaultLogs(@RequestParam("DynamicRequest")String DynamicRequest, @RequestParam(value = "mp", required = false) Integer mp){
        Map<String, Object> response = new HashMap<>();

        try {
            Page<FaultLog> faultLogPage = queryFaultLogService.getFaultLogByCondition(DynamicRequest, mp);

            if(mp != null)      //判断是不是Android端的请求
                response.put("responseData", JSON.toJSONString(faultLogPage.getContent(), SerializerFeature.DisableCircularReferenceDetect));          //返回数据List的JSON字符串
            else
                response.put("responseData", faultLogPage.getContent());          //返回数据List
            response.put("pagesNum", faultLogPage.getTotalPages());           //共多少页
            response.put("pageDataNum", faultLogPage.getNumberOfElements());  //当前页多少数据
            response.put("totalDataNum", faultLogPage.getTotalElements());    //共有多少数据
        } catch (Exception e) {
            e.printStackTrace();
            response.clear();
            response.put("Error",e.getMessage());
        }

        return response;
    }

    /**
     * 获取故障未消除的故障日志
     * @return
     */
    @RequestMapping(value = "/getFaultLogNotClear", method = RequestMethod.GET)
    public List<FaultLog> getFaultLogNotClear(@RequestParam(value = "mp", required = false) Integer mp){
        try {
            return queryFaultLogService.getFaultLogByisClear(false,null, mp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某台设备未消除的故障日志
     * @param id
     * @return
     */
    @RequestMapping(value = "/getFaultLogNotClearOf/{id}", method = RequestMethod.GET)
    public List<FaultLog> getFaultLogNotClearOf(@PathVariable("id") Integer id,@RequestParam(value = "mp", required = false) Integer mp){
        try {
            return queryFaultLogService.getFaultLogByisClear(false, id, mp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 消除某个故障，将日志中的FaultClear置为true，并记录消除时间
     * @param id
     * @return
     */
    @RequestMapping(value = "/clearFault/{id}", method = RequestMethod.POST)
    public String clearFault(@PathVariable("id") Integer id){
        try {
            queryFaultLogService.faultClear(id);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL";
    }

}
