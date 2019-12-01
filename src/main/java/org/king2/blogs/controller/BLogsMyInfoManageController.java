package org.king2.blogs.controller;

import org.king2.blogs.result.SystemResult;
import org.king2.blogs.service.BLogsMyInfoManageService;
import org.king2.blogs.service.impl.BLogsMyInfoManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 我的信息管理Controller
 *
 * @author 鹿七七sama
 */
@Controller
@RequestMapping("/acl/my/info")
public class BLogsMyInfoManageController {

    // 注入我的信息Service
    @Autowired
    private BLogsMyInfoManageService bLogsMyInfoManageService;

    /**
     * 显示我的信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        SystemResult index = bLogsMyInfoManageService.index(request);
        request.setAttribute("result", index);
        return "MyInfo/index";
    }
}
