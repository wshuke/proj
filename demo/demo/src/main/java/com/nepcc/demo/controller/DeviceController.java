package com.nepcc.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nepcc.demo.entity.Device;
import com.nepcc.demo.entity.DeviceType;
import com.nepcc.demo.entity.HistoryData;
import com.nepcc.demo.entity.UrbanArea;
import com.nepcc.demo.service.deviceServices.HistoryDataService;
import com.nepcc.demo.service.deviceServices.QueryDeviceService;
import com.nepcc.demo.service.deviceServices.UploadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DeviceController 负责处理设备和历史数据查询请求及视图处理
 */
@RestController
public class DeviceController {

    @Autowired
    QueryDeviceService queryDeviceService;

    @Autowired
    HistoryDataService historyDataService;

    /**
     * 根据条件动态查询设备信息列表
     * @param DynamicRequest
     * @param mp 如果有mp参数则为Android端请求，返回的responseData为json字符串
     * @return
     */
    @RequestMapping(value = "/getDevices", method = RequestMethod.POST)
    public Map<String, Object> getDevices(@RequestParam(value = "DynamicRequest") String DynamicRequest, @RequestParam(value = "mp", required = false) Integer mp){
        Map<String, Object> response = new HashMap<>();

        try {
            Page<Device> devicePage= null;
            devicePage = queryDeviceService.getDevicesByCondition(DynamicRequest);

            if(mp != null)      //判断是不是Android端的请求
                response.put("responseData", JSON.toJSONString(devicePage.getContent(),SerializerFeature.DisableCircularReferenceDetect));          //返回数据JSON字符串
            else
                response.put("responseData", devicePage.getContent());          //返回数据List
            response.put("pagesNum", devicePage.getTotalPages());           //共多少页
            response.put("pageDataNum", devicePage.getNumberOfElements());  //当前页多少数据
            response.put("totalDataNum", devicePage.getTotalElements());    //共有多少数据
        } catch (Exception e) {
            e.printStackTrace();
            response.clear();
            response.put("Error",e.getMessage());
        }

        return response;
    }

    /**
     * 获取一台设备的铭牌,附加上负责用户的姓名和联系方式
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDeviceInfo/{id}", method = RequestMethod.GET)
    public Map<String, Object> getDeviceInfo(@PathVariable(value = "id") Integer id){

        Device device = queryDeviceService.getDeviceById(id);
        Map<String, Object> descJson = new HashMap<>();

        descJson.put("description", device.getDevDescription());
        descJson.put("userName", device.getUser().getName());
        descJson.put("contact", device.getUser().getContactInformation());

        return descJson;
    }

    /**
     * 获取一台设备的所有信息，user的非必要信息已处理
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDeviceById/{id}", method = RequestMethod.GET)
    public Device getDeviceById(@PathVariable("id") Integer id){
        Device device = queryDeviceService.getDeviceById(id);
        if(device == null)
            return null;
        device.getUser().setPassword("");
        device.getUser().setUserDescription("");
        device.getUser().setPrivilegeValue(0);
        device.getUser().setUserGroup(null);

        return device;
    }

    /**
     * 获取一台设备当前的运行数据
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/getDeviceLatestData/{id}", method = RequestMethod.GET)
    public HistoryData getDeviceLatestData(@PathVariable(value = "id") Integer id){
        return historyDataService.getLatestDataOfDevice(id);
    }

    /**
     * 获取一台设备两个时间点之间的运行数据,最多返回100点数据；
     * 如果符合要求的数据量dataNum为100条以内，则返回dataNum条数据；
     * 如果符合要求的数据量dataNum为100条以上，则每间隔dataNum/50取1条数据，那么：
     * 例如当dataNum为110时，则会返回55条数据；最多的情况下如dataNum为149，那么返回79条数据；dataNum达到150时，返回50条数据；
     * 以此类推。
     * @param id
     * @param dateBetween
     * @return
     */
    @RequestMapping(value = "/getHistoryDataOfDevice/{id}", method = RequestMethod.GET)
    public List<HistoryData> getHistoryDataOfDevice(@PathVariable(value = "id") Integer id,
                                                    @RequestParam(value = "dateBetween") String dateBetween){

        try {
            return historyDataService.getHistoryDataBetweenTimeByDeviceIdWithInterval(dateBetween, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某区域、某类型各状态下的设备台数
     * @param areaId
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/getDeviceNum/{areaId}/{typeId}", method = RequestMethod.GET)
    public Map<String, Object> getDeviceNum(@PathVariable("areaId")Integer areaId,
                                            @PathVariable("typeId")Integer typeId){
        return queryDeviceService.getNumberOfDevicesInDifferentStateFormattedByJson(areaId, typeId);
    }

    @RequestMapping(value = "/getDeviceNum/{typeId}", method = RequestMethod.GET)
    public Map<String, Map<String, Object>> getDeviceNumOfEveryArea(@PathVariable("typeId")Integer typeId){
        Map<String, Map<String, Object>> areaNumsMap = new HashMap<>();

        List<UrbanArea> urbanAreas = queryDeviceService.getAllUrbanAreas(0,10).getContent();
        for(UrbanArea area : urbanAreas){       //遍历所有区，取出各区各个类型设备的各状态台数
            areaNumsMap.put(area.getName(), queryDeviceService.getNumberOfDevicesInDifferentStateFormattedByJson(typeId, area.getId()));
        }

        return areaNumsMap;
    }

    /**
     * 注册一台设备到发起请求的User名下，DeviceState和RegistrationDate不是可填项
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/registOneDevice",method = RequestMethod.POST)
    public Device registOneDevice(HttpServletRequest request, HttpSession session){
        Integer deviceOwnerId = Integer.parseInt(request.getParameter("userId"));
        if(deviceOwnerId == null)
            return null;
        try {
            if(deviceOwnerId == session.getAttribute("userId")){        //是本人才能将设备注册到本人名下
                Device device = queryDeviceService.modifyDeviceInfo(new Device(), request);
                device.getUser().setPassword(null);
                return device;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 修改设备信息，除了id，state，以及registrationDate，其他的都可以修改
     * @param id
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/modifyDevice/{id}", method = RequestMethod.POST)
    public Device modifyDevice(@PathVariable("id") Integer id, HttpServletRequest request, HttpSession session){
        try {
            Device device = queryDeviceService.getDeviceById(id);
            if(device == null)
                return null;
            if(session.getAttribute("userId").equals(device.getUser().getId())){    //判断是不是该user的设备
                device = queryDeviceService.modifyDeviceInfo(device, request);
                device.getUser().setPassword(null);
                return device;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Autowired
    UploadDataService uploadDataService;
    /**
     * 物联网设备上传数据，如果有指令则下发指令，如果有故障则推送消息
     * @return msg:msg的格式 -- "x_x" ：
     *      -->前一个数字代表上传数据的结果 -- "0":数据和故障均未上传成功;"1":数据上传成功，故障未上传成功;"2":故障上传成功，数据未上传成功;"3":数据和故障均上传成功
     *      -->后一个数字代表下发的指令值
     */
    @RequestMapping(value = "/uploadDataOfDevice/{id}", method = RequestMethod.POST)
    public String uploadDataOfDevice(@PathVariable(value = "id") Integer id, @RequestParam(value = "uploadData") String uploadData){
        String msg="";
        msg = uploadDataService.saveOneDataOfDevice(id,uploadData);     //先尝试保存数据与故障信息，看是否成功
        if(msg.equals("2") || msg.equals("3")){     //表明有故障信息
            //推送消息！待写
        }

        msg = msg + "_";
        //如果有指令则在msg后面添加上指令，待写
        msg = msg + "0";

        return msg;
    }

}
