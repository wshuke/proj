package com.nepcc.demo.service.FaultLogService;

import com.alibaba.fastjson.JSON;
import com.nepcc.demo.dao.DeviceDao;
import com.nepcc.demo.dao.FaultLogDao;
import com.nepcc.demo.dao.FaultLogSpecDao;
import com.nepcc.demo.entity.Device;
import com.nepcc.demo.entity.FaultLog;
import com.nepcc.demo.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.*;

@Service
public class QueryFaultLogService {

    @Autowired
    FaultLogDao faultLogDao;

    @Autowired
    FaultLogSpecDao faultLogSpecDao;

    @Autowired
    GeneralService generalService;

    @Autowired
    DeviceDao deviceDao;

    /**
     * 查询故障日志(Faultlog)的通用方法，筛选条件可选，仅需将需要的筛选条件加入到jsonStr即可
     * @param jsonStr 装载所有可选检索条件的Json数据
     * @return List<FaultLog>  其中，每个FaultLog的device都已置为null
     * @throws ParseException
     */
    public Page<FaultLog> getFaultLogByCondition(String jsonStr, Integer mp) {
        Map<String, Object> map = JSON.parseObject(jsonStr);

        //查看分页参数是否存在，不存在则做默认处理
        Integer page = generalService.processQueryPage(map);
        Integer pageSize = generalService.processQueryPageSize(map);

        Page<FaultLog> faultLogPage = faultLogSpecDao.findByCondition(page, pageSize,
                                                                    (List<Map<String, Object>>) map.get("sortBy"),
                                                                    (Map<String, Object>) map.get("equalBy"),
                                                                    (Map<String, Object>) map.get("betweenBy"));
        List<FaultLog> list = faultLogPage.getContent();
        if(mp!=null)
            processFaultLogMp(list);
        else
            processFaultLog(list);

        return faultLogPage;
    }

    /**
     * 根据故障消除的情况来筛选日志,可以进一步通过设备id来筛选，不使用id则传入null
     * @param bool
     * @return
     */
    public List<FaultLog> getFaultLogByisClear(Boolean bool, Integer id, Integer mp){
        List<FaultLog> list;
        if(id == null)
            list = faultLogDao.findFaultLogsByIsFaultClear(bool);
        else
            list = faultLogDao.findFaultLogsByIsFaultClearAndDevice(bool, deviceDao.findDeviceById(id));
        if(list == null || list.isEmpty())
            return null;

        if(mp!=null)
            processFaultLogMp(list);
        else
            processFaultLog(list);  //将device信息去除，减少传输的数据量
        return list;
    }

    /**
     *  带mp参数时，不传device而只传deviceId
     * @param list
     */
    public void processFaultLog(List<FaultLog> list){
        Iterator it = list.iterator();      //因为Device实体的数据量会很大，但查看FaultLog时实际上并不需要Device的所有信息，所以遍历list将所有device置为null
        while(it.hasNext()){
            FaultLog fl = ((FaultLog)it.next());

            Device d = fl.getDevice();      //清除device中的无用信息，减少数据量
            d.setUser(null);
            d.setDevDescription("");
            d.setRegistrationDate(null);

            if(fl.getFaultDealUser() != null){
                fl.setFaultDealUserName(fl.getFaultDealUser().getName());
                fl.setFaultDealUser(null);
            }
        }
    }
    public void processFaultLogMp(List<FaultLog> list){
        Iterator it = list.iterator();      //因为Device实体的数据量会很大，但查看FaultLog时实际上并不需要Device的所有信息，所以遍历list将所有device置为null
        while(it.hasNext()){
            FaultLog fl = ((FaultLog)it.next());
            fl.setDeviceId(fl.getDevice().getId()); //装入临时属性deviceId以替换整个Device
            fl.setDevice(null);
            if(fl.getFaultDealUser() != null){
                fl.setFaultDealUserName(fl.getFaultDealUser().getName());
                fl.setFaultDealUser(null);
            }
        }
    }

    public void faultClear(Integer id){
        FaultLog faultLog = faultLogDao.findFaultLogById(id);
        if(faultLog.getFaultClear() == false){
            faultLog.setFaultClear(true);
            faultLog.setFaultClearTime(new Date());
            faultLogDao.saveAndFlush(faultLog);
        }
    }

}
