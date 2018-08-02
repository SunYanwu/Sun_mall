package com.sunmall.controller.portal;

import com.sunmall.common.Const;
import com.sunmall.common.ServiceResponse;
import com.sunmall.pojo.User;
import com.sunmall.services.IUserService;
import com.sunmall.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @className UserController
 * @Author SunYanwu
 * @Description：
 * @Date 2018/7/24 0:17
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;   //注入Services层的对象
    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> login(String username,String password,HttpSession session){
        //controller-Services-Dao-database
        ServiceResponse<User> response=iUserService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value="logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> loginout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServiceResponse.createBySucess();
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public ServiceResponse<String> register(User user){
        ServiceResponse response=iUserService.register(user);
        return response;
    }
}
