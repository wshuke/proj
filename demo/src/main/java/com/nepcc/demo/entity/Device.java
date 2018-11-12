package com.nepcc.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "device_id", nullable = false)
    private Integer id;

    @Column(name = "device_name", nullable = false)
    private String name;

    //join column

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", nullable = false)
    private DeviceType deviceType;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_state_id", nullable = false)
    private DeviceState deviceState;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "urban_area_id", nullable = false)
    private UrbanArea urbanArea;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //join column end

    @Column(name = "device_description")
    private String devDescription;      //设备铭牌，处理为JSON字符串

    @Column(name = "device_position", nullable = false)
    private String devPosition;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public DeviceState getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    public UrbanArea getUrbanArea() {
        return urbanArea;
    }

    public void setUrbanArea(UrbanArea urbanArea) {
        this.urbanArea = urbanArea;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDevDescription() {
        return devDescription;
    }

    public void setDevDescription(String devDescription) {
        this.devDescription = devDescription;
    }

    public String getDevPosition() {
        return devPosition;
    }

    public void setDevPosition(String devPosition) {
        this.devPosition = devPosition;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registerationDate) {
        this.registrationDate = registerationDate;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deviceType=" + deviceType +
                ", deviceState=" + deviceState +
                ", urbanArea=" + urbanArea +
                ", user=" + user +
                ", devDescription='" + devDescription + '\'' +
                ", devPosition='" + devPosition + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
