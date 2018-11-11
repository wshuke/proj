package com.nepcc.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "urban_areas")
public class UrbanArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "urban_area_id", nullable = false)
    private Integer id;

    @Column(name = "urban_area_name", nullable = false)
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
        return "UrbanArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
