package com.user.auth.server.client;

import com.user.api.IUserInfoService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "userServer")
public interface UserInfoFeignClient extends IUserInfoService {
}
