package com.nepcc.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  在FaultLog中将device_id定义为一个Device实例是为了借助Device的其他属性来联表查询，实现结果筛选，比如借助Device的UrbanArea，User,Id 等属性进行筛选
 *  但往前端下发Json数据时，Device实例数据量太大，除了其id 属性外，其他属性根本没有必要存在，
 *  因此定义一个临时属性 deviceId 来替代整个Device实例，传递给前端
 */
@Entity
@Table(name = "fault_log")
public class FaultLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "fault_log_id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "fault_type_id", nullable = false)
    private FaultType faultType;

    @Column(name = "fault_value", columnDefinition = "float(6,1) default '0.0'", nullable = false)
    private BigDecimal faultValue;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fault_time", nullable = false)
    private Date faultTime;

    @Column(name = "fault_clear", nullable = false)
    private Boolean isFaultClear;

    //后面的3项是故障处理之后才会有的，所以nullable = true
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fault_clear_time")
    private Date faultClearTime;

    @Column(name = "maintenance_info")
    private String maintenanceInfo;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "fault_deal_by")
    private User faultDealUser;

    @Transient
    private Integer deviceId;       //临时变量，不会持久化，仅用于替代Device实例

    @Transient
    private String faultDealUserName;    //临时变量，不会持久化，仅用于替代User实例

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public FaultType getFaultType() {
        return faultType;
    }

    public void setFaultType(FaultType faultType) {
        this.faultType = faultType;
    }

    public Date getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(Date faultTime) {
        this.faultTime = faultTime;
    }

    public BigDecimal getFaultValue() {
        return faultValue;
    }

    public void setFaultValue(BigDecimal faultValue) {
        this.faultValue = faultValue;
    }

    public Boolean getFaultClear() {
        return isFaultClear;
    }

    public void setFaultClear(Boolean faultClear) {
        isFaultClear = faultClear;
    }

    public Date getFaultClearTime() {
        return faultClearTime;
    }

    public void setFaultClearTime(Date faultClearTime) {
        this.faultClearTime = faultClearTime;
    }

    public String getMaintenanceInfo() {
        return maintenanceInfo;
    }

    public void setMaintenanceInfo(String maintanenceInfo) {
        this.maintenanceInfo = maintanenceInfo;
    }

    public User getFaultDealUser() {
        return faultDealUser;
    }

    public void setFaultDealUser(User faultDealUser) {
        this.faultDealUser = faultDealUser;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getFaultDealUserName() {
        return faultDealUserName;
    }

    public void setFaultDealUserName(String faultDealUserName) {
        this.faultDealUserName = faultDealUserName;
    }

    @Override
    public String toString() {
        return "FaultLog{" +
                "id=" + id +
                ", device=" + device +
                ", faultType=" + faultType +
                ", faultValue=" + faultValue +
                ", faultTime=" + faultTime +
                ", isFaultClear=" + isFaultClear +
                ", faultClearTime=" + faultClearTime +
                ", maintenanceInfo='" + maintenanceInfo + '\'' +
                ", faultDealUser=" + faultDealUser +
                ", deviceId=" + deviceId +
                '}';
    }

    public FaultLog() {
    }

    public FaultLog(Device device, FaultType faultType, Date faultTime, Boolean isFaultClear, BigDecimal faultValue) {
        this.device = device;
        this.faultType = faultType;
        this.faultTime = faultTime;
        this.isFaultClear = isFaultClear;
        this.faultValue = faultValue;
    }
}
