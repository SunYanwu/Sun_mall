package com.sunmall.dao;

import com.sunmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);    //判断用户名是否存在

    int checkEmail(String emailName);   //判断Email是否存在

    User selectLogin(@Param("username") String username,@Param("password") String password); //根据用户名和密码查询user信息


}