package org.king2.blogs.appoint;

import org.king2.blogs.dto.BLogsIndexDto;
import org.king2.blogs.enums.BLogSystemState;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.utils.JsonUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 博客拦截器的委托类
 */
public class BLogsInterAppoint {


    /**
     * 判断用户是否登录 并返回用户信息
     *
     * @param request 请求
     * @return 返回是否登录
     */
    public static SystemResult checkUserIfLogin(String userCookieInfo, HttpServletRequest request) {


        if (StringUtils.isEmpty(userCookieInfo)) {
            // 为空说明没有登录
            return new SystemResult(BLogSystemState.USER_NO_LOGIN.getVALUE(), "用户没有登录");
        }

        // 用户登录了
        BLogsIndexDto userInfoDto = null;
        try {
            userInfoDto = JsonUtils.jsonToPojo(userCookieInfo, BLogsIndexDto.class);
        } catch (Exception e) {
            return new SystemResult(BLogSystemState.USER_EDIT_COOKIE.getVALUE(), "请勿修改Cookie信息");
        }

        // 用户缓存被清 ， 用户没有登录
        if (userInfoDto == null) {
            return new SystemResult(BLogSystemState.USER_NO_LOGIN.getVALUE(), "用户没有登录");
        }

        // 登录成功 返回数据
        return new SystemResult(BLogSystemState.SUCCESS.getVALUE(), BLogSystemState.SUCCESS.getKEY(), userInfoDto);

    }

}
