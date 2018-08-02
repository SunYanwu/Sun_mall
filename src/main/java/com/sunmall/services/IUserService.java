package com.sunmall.services;

import com.sunmall.common.ServiceResponse;
import com.sunmall.pojo.User;

public interface IUserService {
    public ServiceResponse<User> login(String username, String password); //用户登录
    public ServiceResponse<String> register(User user);  //用户注册
}
