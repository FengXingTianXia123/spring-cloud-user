package com.user.gateway;

import com.user.entity.UserRecordVo;
import com.user.gateway.client.UserInfoAuthClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener {
    @Autowired
    private UserInfoAuthClient userInfoAuthClient;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("----create session---------");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("jin ru session chao shi");
        HttpSession session=httpSessionEvent.getSession();
        String sessionId=session.getId();
        UserRecordVo record=new UserRecordVo();
        record.setSessionId(sessionId);
        try {
            userInfoAuthClient.updateUserRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
