package com.nepcc.demo.dao;

import com.nepcc.demo.entity.HistoryData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HistoryDataDao extends CrudRepository<HistoryData, Integer> {

    HistoryData save(HistoryData hd);       //保存一组历史数据

    //@Query(value = "select * from history_datas where dev_id = ?1", nativeQuery = true)
    List<HistoryData> findAllByDeviceId(Integer id);   //利用设备id找到该设备所有历史数据


    @Query(value = "select * from history_datas where data_id=" +
            "(select max(data_id) from history_datas where device_id = ?1)" , nativeQuery = true)
    HistoryData findLatestByDeviceId(Integer id);      //利用设备id找到该设备最新的历史数据


    @Query(value = "select * from history_datas where" +
            " (data_time between ?1 and ?2) " +
            " and device_id=?3", nativeQuery = true)
    List<HistoryData> findBetweenDateByDevId(Date date1, Date date2, Integer id);
    //^利用设备id找到该设备某段时间内的历史数据
    List<HistoryData> findByDeviceIdAndDataTimeBetween(Integer id, Date date1, Date date2);
    //^利用设备id找到该设备某段时间内的历史数据量
    Long countHistoryDataByDeviceIdAndDataTimeBetween(Integer id, Date date1, Date date2);

    //在findByDeviceIdAndDataTimeBetween的基础上加入时间间隔的筛选
    @Query(value = "select * from " +
            "(select (@i\\:=@i+1)p,hd.* from history_datas as hd,(select @i\\:=0)t where device_id=?1 and (data_time between ?2 and ?3)) " +
            "as d where MOD(d.p,?4)=1", nativeQuery = true)
    List<HistoryData> findByDeviceIdAndDataTimeBetweenAndInterval(Integer id, Date date1, Date date2, Integer interval);
}
