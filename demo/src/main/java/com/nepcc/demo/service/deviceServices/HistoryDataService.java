package com.nepcc.demo.service.deviceServices;

import com.alibaba.fastjson.JSON;
import com.nepcc.demo.dao.HistoryDataDao;
import com.nepcc.demo.entity.HistoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HistoryDataService {

    @Autowired
    HistoryDataDao historyDataDao;

    /**
     * 查询id为devId的设备在两个时间点之间的运行数据
     * @param dateBetween
     * @param devId
     * @return List<HistoryData>
     * @throws ParseException
     */
    public List<HistoryData> getHistoryDataBetweenTimeByDeviceId(String dateBetween, Integer devId) throws ParseException {
        Map<String, Object> map;
        map = JSON.parseObject(dateBetween);        //从字符串中解析出date1 和 date2

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = df.parse((String) map.get("date1"));
        Date d2 = df.parse((String) map.get("date2"));//利用parse将其解析为Date实例


        //return historyDataDao.findBetweenDateByDevId(d1, d2, devId);
        return  historyDataDao.findByDeviceIdAndDataTimeBetween(devId, d1, d2);
    }

    /**
     * 获取最新的运行数据
     * 如果该数据的记录时间距离当前时间太久远，则表示其并非当前实时运行数据返回null
     * @param id
     * @return
     */
    public HistoryData getLatestDataOfDevice(Integer id){
        HistoryData hd = historyDataDao.findLatestByDeviceId(id);
        /*如果该数据的记录时间距离当前时间太久远，则表示其并非当前实时运行数据返回null*/

        hd.setDataTime(null);
        return hd;
    }


    /**
     * 自动产生间隔，取某id设备的历史数据，需指定查询的时间范围
     * @param dateBetween
     * @param devId
     * @return
     * @throws ParseException
     */
    public List<HistoryData> getHistoryDataBetweenTimeByDeviceIdWithInterval(String dateBetween, Integer devId) throws ParseException {
        Map<String, Object> map = JSON.parseObject(dateBetween);        //从字符串中解析出date1 和 date2

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = df.parse((String) map.get("date1"));
        Date d2 = df.parse((String) map.get("date2"));//利用parse将其解析为Date实例

        Long count = historyDataDao.countHistoryDataByDeviceIdAndDataTimeBetween(devId, d1, d2);

        if(count == 0)
            return null;
        else if(count <= 100)
            return historyDataDao.findByDeviceIdAndDataTimeBetween(devId, d1, d2);
        else
            return historyDataDao.findByDeviceIdAndDataTimeBetweenAndInterval(devId, d1, d2, count.intValue()/50);
    }

}
