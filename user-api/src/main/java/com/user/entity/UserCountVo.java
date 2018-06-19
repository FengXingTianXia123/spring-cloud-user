package com.user.entity;

import java.io.Serializable;

public class UserCountVo implements Serializable{
    private static final long serialVersionUID = 4672298123223593671L;

    private String queryType;
    private Long peopleCount;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Long getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Long peopleCount) {
        this.peopleCount = peopleCount;
    }


}
