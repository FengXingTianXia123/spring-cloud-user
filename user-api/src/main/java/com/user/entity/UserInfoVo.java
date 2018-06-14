package com.user.entity;

import java.io.Serializable;

public class UserInfoVo implements Serializable{

    private static final long serialVersionUID = -1443091008264579250L;

    private Long id;

    private String name;

    private String realName;

    private Boolean gender;

    private Long age;

    private UserRecordVo userRecordVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public UserRecordVo getUserRecordVo() {
        return userRecordVo;
    }

    public void setUserRecordVo(UserRecordVo userRecordVo) {
        this.userRecordVo = userRecordVo;
    }
}
