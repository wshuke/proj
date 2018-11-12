package com.nepcc.demo.entity;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用Hibernate时一定要用IDENTITY
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private String count;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "privilege_value", nullable = false)
    private Integer privilegeValue;

    @Column(name = "user_description")
    private String userDescription;

    @Column(name = "contact_information")
    private String contactInformation;

    //join column

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;

    //join coloumn end

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPrivilegeValue() {
        return privilegeValue;
    }

    public void setPrivilegeValue(Integer privilegeValue) {
        this.privilegeValue = privilegeValue;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", password='" + password + '\'' +
                ", privilegeValue=" + privilegeValue +
                ", userDescription='" + userDescription + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", userGroup=" + userGroup +
                '}';
    }
}
