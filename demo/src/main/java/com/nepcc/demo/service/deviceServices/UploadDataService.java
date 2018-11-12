package com.nepcc.demo.service.deviceServices;

import com.alibaba.fastjson.JSON;
import com.nepcc.demo.dao.DeviceDao;
import com.nepcc.demo.dao.FaultLogDao;
import com.nepcc.demo.dao.FaultTypeDao;
import com.nepcc.demo.dao.HistoryDataDao;
import com.nepcc.demo.entity.Device;
import com.nepcc.demo.entity.FaultLog;
import com.nepcc.demo.entity.FaultType;
import com.nepcc.demo.entity.HistoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UploadDataService {

    @Autowired
    HistoryDataDao historyDataDao;
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    FaultLogDao faultLogDao;
    @Autowired
    FaultTypeDao faultTypeDao;

    /**
     * 存储物联网设备上传的数据
     * 返回格式定义："0":数据和故障均未上传成功;"1":数据上传成功，故障未上传成功;"2":故障上传成功，数据未上传成功;"3":数据和故障均上传成功
     * @param id
     * @param uploadDataJson
     * @return String msg
     */
    public String saveOneDataOfDevice(Integer id, String uploadDataJson){
        Integer msg = 0;        //用于传递信息
        Device device;

        Map<String, Object>  uploadData = null;
        try {
            uploadData = (Map<String, Object>) JSON.parse(uploadDataJson);
            String pwd = (String) uploadData.get("pwd");        //取出密码
            if(pwd == null || pwd.equals(""))
                return "0";
            device = deviceDao.findDeviceById(id);              //根据设备ID查询出该设备以进行匹配
            if(!device.getUser().getPassword().equals(pwd))     //密码与所属用户密码必须一致
                return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

        Date date = new Date();

        /*
        上传数据：成功则msg+1，失败msg仍为0
         */
        try {
            List<Map<String, Object>> dataJson = (List<Map<String, Object>>) uploadData.get("dJ");  //取出实时数据
            if(dataJson != null){
                //验证数据格式是否正确
                for(Map<String, Object> data : dataJson){
                    if(data.size() < 2 || !data.containsKey("u"))
                        throw new Exception("数据格式不符合要求");
                }
                //到达这里表明数据校验正确

                HistoryData hd = new HistoryData(date, id, JSON.toJSONString(dataJson));            //装入当前时间和设备ID以及数据JSON
                historyDataDao.save(hd);
                msg = msg + 1;   //数据上传成功
            }
        } catch (Exception e) {                                                     //若json解析出异常，或数据库写入出错，不应该保存该数据
            e.printStackTrace();
        }
        /*
        上传故障：成功则msg+2，失败msg仍为0
         */
        try {
            Integer faultTypeId = (Integer) uploadData.get("fTId");
            BigDecimal faultValue = (BigDecimal) uploadData.get("fV");              //故障为浮点型，保留1位小数，json解析后为BigDecimal
            if(faultTypeId != null && faultTypeId != 0){                            //防止空变量出现
                FaultType faultType = faultTypeDao.findFaultTypeById(faultTypeId);
                FaultLog fl = new FaultLog(device, faultType, date, false, faultValue);
                faultLogDao.save(fl);
                msg = msg + 2;      //故障上传成功
            }
        }catch (Exception e) {                                                      //若数据库读取或写入出错，不应该保存该数据
            e.printStackTrace();
        }

        return msg.toString();                                                      //返回msg
    }

}
