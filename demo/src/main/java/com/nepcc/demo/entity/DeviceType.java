package com.nepcc.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "dev_types")
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "device_type_id", nullable = false)
    private Integer id;

    @Column(name = "device_type_name", nullable = false)
    private String name;

    @Column(name = "device_type_description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
