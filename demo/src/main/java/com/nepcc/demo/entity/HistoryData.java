package com.nepcc.demo.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history_datas")
public class HistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "data_id", nullable = false)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "data_time", nullable = false)
    private Date dataTime;

    //@ManyToOne  MERGE
    @Column(name = "device_id", nullable = false)   //由于Device的数据量很大，且调取历史记录时一般不需要调取device的具体信息，考虑之后决定不使用JoinColumn
    private Integer deviceId;

    @Column(name = "data_json", nullable = false)
    private String dataJson;      //设备运行数据，处理为JSON字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }


    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "id=" + id +
                ", dataTime=" + dataTime +
                ", deviceId=" + deviceId +
                ", dataJson='" + dataJson + '\'' +
                '}';
    }

    public HistoryData(Date dataTime, Integer deviceId, String dataJson) {
        this.dataTime = dataTime;
        this.deviceId = deviceId;
        this.dataJson = dataJson;
    }

    public HistoryData() {
    }
}
