package com.nepcc.demo.entity;

import javax.persistence.*;

@Table(name = "user_groups")
@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "user_group_id", nullable = false)
    private Integer id;

    @Column(name = "user_group_name", nullable = false)
    private String userGroupName;

    @Column(name = "user_group_description", nullable = false)
    private String userGroupDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserGroupDescription() {
        return userGroupDescription;
    }

    public void setUserGroupDescription(String userGroupDescription) {
        this.userGroupDescription = userGroupDescription;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", userGroupName='" + userGroupName + '\'' +
                ", userGroupDescription='" + userGroupDescription + '\'' +
                '}';
    }
}
