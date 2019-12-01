package org.king2.blogs.controller;

import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.service.BLogsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * =======================================================
 * 说明:    用户操作的Controller
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.21  		创建
 * <p>
 * =======================================================
 */
@Controller
@RequestMapping("/logs/user")
public class BLogsUserController {

    // 注入Service
    @Autowired
    private BLogsUserService bLogsUserService;

    /**
     * -----------------------------------------------------
     * 功能:  用户的登录
     * <p>
     * 参数:
     * userName     String      用户名
     * passWord     String      密码
     * <p>
     * 返回: SystemResult         返回登录的结果
     * -----------------------------------------------------
     */
    @RequestMapping("/login")
    @ResponseBody
    public SystemResult login(String userName, String passWord,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 调用service
        SystemResult login = bLogsUserService.login(userName, passWord, response, request);
        return login;
    }

    /**
     * -----------------------------------------------------
     * 功能:  用户的注册
     * <p>
     * 参数:
     * bLogsUser     BLogsUser    用户注册的信息
     * <p>
     * 返回: SystemResult         返回注册的结果
     * -----------------------------------------------------
     */
    @RequestMapping("/register")
    @ResponseBody
    public SystemResult register(BLogsUser bLogsUser) {

        // 调用service
        SystemResult register = bLogsUserService.register(bLogsUser);
        return register;
    }
}
