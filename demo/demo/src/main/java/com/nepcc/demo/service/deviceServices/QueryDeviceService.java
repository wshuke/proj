package com.nepcc.demo.service.deviceServices;

import com.alibaba.fastjson.JSON;
import com.nepcc.demo.dao.*;
import com.nepcc.demo.entity.Device;
import com.nepcc.demo.entity.DeviceType;
import com.nepcc.demo.entity.FaultType;
import com.nepcc.demo.entity.UrbanArea;
import com.nepcc.demo.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class QueryDeviceService {

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    DeviceSpecDao deviceSpecDao;

    @Autowired
    GeneralService generalService;

    public void addDevice(Device device){
        deviceDao.save(device);
    }

    public String getDeviceDescription(Integer id){
        return deviceDao.findDeviceDescritptionById(id);
    }

    public Device getDeviceById(Integer id){
        return deviceDao.findDeviceById(id);
    }

    public List<Device> listAllDevicesOfUser(Integer id){
        return deviceDao.findAllByUserId(id);
    }

    /**
     * 查询设备(Device)的通用方法，筛选条件可选，仅需将需要的筛选条件加入到jsonStr即可
     * @param jsonStr
     * @return List<Device> Device实例序列化后的数据量会比较大，可根据情况将部分关联实例置为null
     */
    public Page<Device> getDevicesByCondition(String jsonStr){
        Map<String, Object> map =  JSON.parseObject(jsonStr);

        //查看分页参数是否存在，不存在则做默认处理
        Integer page = generalService.processQueryPage(map);
        Integer pageSize = generalService.processQueryPageSize(map);

        //跟筛选和排序规则查询数据
        Page<Device> devicePage = deviceSpecDao.findByConditions((String) map.get("search"),
                                            (List<Map<String, Object>>) map.get("sortBy"),
                                            (Map<String, Object>) map.get("equalBy"),
                                            (Map<String, Object>) map.get("betweenBy"),
                                            page, pageSize);
        Iterator<Device> it = devicePage.getContent().iterator();
        while(it.hasNext()){
            Device d = it.next();
            d.getUser().setPassword("");
            d.getUser().setUserGroup(null);
            d.getUser().setUserDescription("");
            d.setDevDescription(null);          //为减少不必要的传输数据，将铭牌数据去除，获取铭牌数据请使用/getDeviceInfo/{id}
        }

        return devicePage;
    }

    /**
     * 获取各个状态下的某个地区、某种类型设备数量
     * @param areaId
     * @param typeId
     * @return
     */
    public Map<String, Object> getNumberOfDevicesInDifferentStateFormattedByJson(Integer areaId, Integer typeId){
        Long runNumber = deviceDao.countDevicesbyState(typeId, areaId, 1);
        Long faultNumber = deviceDao.countDevicesbyState(typeId, areaId, 2);
        Long stopNumber = deviceDao.countDevicesbyState(typeId, areaId, 3);

        Map<String, Object> map = new HashMap<>();
        map.put("runNumber", runNumber);
        map.put("faultNumber", faultNumber);
        map.put("stopNumber", stopNumber);

        return map;

    }

    @Autowired
    UserDao userDao;
    @Autowired
    DeviceTypeDao deviceTypeDao;
    @Autowired
    UrbanAreaDao urbanAreaDao;
    @Autowired
    DeviceStateDao deviceStateDao;

    /**
     * 更改设备信息
     * @param device
     * @param request
     * @return
     */
    public Device modifyDeviceInfo(Device device, HttpServletRequest request){
        if(device.getId() == null){     //一个新的Device
            device.setDeviceState(deviceStateDao.findDeviceStateById(3));   //初始状态为正常停运
            device.setRegistrationDate(new Date());                         //产生当前日期作为注册日期
        }
        device.setName(request.getParameter("name"));
        device.setUser(userDao.findUserById(Integer.parseInt(request.getParameter("userId"))));
        device.setDeviceType(deviceTypeDao.findDeviceTypeById(Integer.parseInt(request.getParameter("typeId"))));
        device.setUrbanArea(urbanAreaDao.findUrbanAreaById(Integer.parseInt(request.getParameter("areaId"))));
        device.setDevDescription(request.getParameter("desc"));
        device.setDevPosition(request.getParameter("pos"));
        return deviceDao.saveAndFlush(device);
    }


    public Page<DeviceType> getAllDeviceTypes(Integer page, Integer pageSize){
        if(page == null || pageSize == null){
            page = 0;       //默认返回第1页
            pageSize = 10;  //默认每页10个数据
        }

        return deviceTypeDao.findAll(PageRequest.of(page, pageSize));
    }

    public Page<UrbanArea> getAllUrbanAreas(Integer page, Integer pageSize){
        if(page == null || pageSize == null){
            page = 0;       //默认返回第1页
            pageSize = 10;  //默认每页10个数据
        }

        return urbanAreaDao.findAll(PageRequest.of(page, pageSize));
    }

    @Autowired
    FaultTypeDao faultTypeDao;

    public Page<FaultType> getAllFaultTypes(Integer page, Integer pageSize){
        if(page == null || pageSize == null){
            page = 0;       //默认返回第1页
            pageSize = 10;  //默认每页10个数据
        }

        return faultTypeDao.findAll(PageRequest.of(page, pageSize));
    }
}
