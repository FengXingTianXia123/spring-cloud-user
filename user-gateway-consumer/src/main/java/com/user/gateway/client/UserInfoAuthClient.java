package com.user.gateway.client;

import com.user.api.IUserAuthService;
import com.user.api.IUserInfoService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "authServer")
public interface UserInfoAuthClient extends IUserAuthService {
}
