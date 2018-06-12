package com.user.auth.server.service;

import com.user.api.IUserAuthService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements IUserAuthService {
    @Override
    public String getToken(Integer integer) throws Exception {
        return "1123";
    }
}
