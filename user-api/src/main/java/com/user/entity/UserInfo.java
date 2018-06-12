package com.user.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{

    private static final long serialVersionUID = -1443091008264579250L;

    public long id;
    public String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
