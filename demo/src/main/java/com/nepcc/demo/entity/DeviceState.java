package com.nepcc.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "dev_states")
public class DeviceState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "device_state_id", nullable = false)
    private Integer id;

    @Column(name = "device_state_name", nullable = false)
    private String name;

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

    @Override
    public String toString() {
        return "DeviceState{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
