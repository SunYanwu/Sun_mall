package com.sunmall.services.impl;

import com.sunmall.common.Const;
import com.sunmall.common.ServiceResponse;
import com.sunmall.dao.UserMapper;
import com.sunmall.pojo.User;
import com.sunmall.services.IUserService;
import com.sunmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className UserServiceImpl
 * @Author SunYanwu
 * @Description：
 * @Date 2018/7/24 0:45
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    //使用注解注入dao层关于User的对象
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录的Services层方法实现
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServiceResponse<User> login(String username,String password) {
        //进行用户名是否存在的判断
        int count=userMapper.checkUsername(username);
        if(count==0){
            return ServiceResponse.createByErrorMessage("用户名不存在！");
        }
        // MD5加密
        String MD5password=MD5Util.MD5EncodeUtf8(password);
        //盘点密码是否正确(因为数据库中存储的是加密后的密码，所以这里传入的要是加密后的)
        User user=userMapper.selectLogin(username,MD5password);
        if(user==null){
            return ServiceResponse.createByErrorMessage("密码错误");
        }
        //以上两步都成功，证明信息正确，将密码置为空后，返回user
        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySucess("登录成功",user);
    }

    /**
     * 用户注册的Service层实现
     * @param user
     * @return
     */
    @Override
    public ServiceResponse<String> register(User user){
        //判断用户名是否存在
        int resultCount=userMapper.checkUsername(user.getUsername());
        if(resultCount>0){
            return ServiceResponse.createByErrorMessage("用户名已存在");
        }

        //判断邮箱是否存在
        resultCount=userMapper.checkEmail(user.getEmail());
        if(resultCount>0){
            return ServiceResponse.createByErrorMessage("邮箱已被注册");
        }
        //以上两步均已通过，则进行注册操作
        //设置用户角色
        user.setRole(Const.role.ROLE_COSTUMER);
        //对密码进行MD5加密再存入数据库
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        resultCount=userMapper.insert(user);
        if(resultCount==0){
            return ServiceResponse.createByErrorMessage("注册失败！");
        }
        return ServiceResponse.createBySucess("注册成功");
    }
}
