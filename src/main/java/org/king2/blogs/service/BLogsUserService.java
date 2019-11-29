package org.king2.blogs.service;

import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.result.SystemResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * =======================================================
 * 说明:    用户操作的Service
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.21  		创建
 * <p>
 * =======================================================
 */
public interface BLogsUserService {

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
    SystemResult login(String userName, String passWord, HttpServletResponse response, HttpServletRequest re
    ) throws Exception;

    /**
     * -----------------------------------------------------
     * 功能:  用户的注册
     * <p>
     * 参数:
     * bLogsUser     BLogsUser      用户注册的信息
     * <p>
     * 返回: SystemResult         返回注册的结果
     * -----------------------------------------------------
     */
    SystemResult register(BLogsUser bLogsUser);
}
