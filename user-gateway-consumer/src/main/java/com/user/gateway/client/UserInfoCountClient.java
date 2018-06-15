package com.user.gateway.client;

import com.user.api.IUserCountService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "countServer")
public interface UserInfoCountClient extends IUserCountService {
}
