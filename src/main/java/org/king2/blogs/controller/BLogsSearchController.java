package org.king2.blogs.controller;

import org.king2.blogs.service.BLogsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章搜索的Controller
 *
 * @author 鹿七七sama
 */
@Controller
@RequestMapping("/logs/search")
public class BLogsSearchController {

    // 注入我们的Service
    @Autowired
    private BLogsSearchService bLogsSearchService;

    /**
     * 显示搜索文章的页面
     *
     * @param query 查询的条件
     * @return
     */
    @RequestMapping("/index")
    public String index(String query, HttpServletRequest request) {

        // 存入Request
        request.setAttribute("result", bLogsSearchService.getByQuery(query));
        return "search";
    }
}
