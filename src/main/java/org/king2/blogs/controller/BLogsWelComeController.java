package org.king2.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * =======================================================
 * 说明:    博客欢迎页的Controller
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.21  		创建
 * <p>
 * =======================================================
 */
@Controller
public class BLogsWelComeController {


    /**
     * -----------------------------------------------------
     * 功能:  博客欢迎页的显示
     * <p>
     * 参数:
     * <p>
     * 返回: String
     * -----------------------------------------------------
     */
    @RequestMapping("/")
    public String welcome(HttpServletRequest request) {

        // 尝试从Request中获取用户信息
        Object user = request.getAttribute("user");
        request.setAttribute("flag", user != null);
        return "welcome";
    }
}
