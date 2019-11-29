package org.king2.blogs.controller;

import org.king2.blogs.service.BLogsIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * =======================================================
 * 说明:    博客首页
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.23  		创建
 * <p>
 * =======================================================
 */
@Controller
@RequestMapping("/logs")
public class BLogsIndexController {

    // 注入博客首页Service
    @Autowired
    private BLogsIndexService bLogsIndexService;

    /**
     * -----------------------------------------------------
     * 功能:  博客首页的显示
     * <p>
     * 参数:
     * <p>
     * 返回: String
     * -----------------------------------------------------
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) throws Exception {

        request.setAttribute("result", bLogsIndexService.index(request));
        return "index";
    }
}
