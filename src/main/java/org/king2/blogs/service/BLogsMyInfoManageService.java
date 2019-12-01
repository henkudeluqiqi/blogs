package org.king2.blogs.service;

import org.king2.blogs.result.SystemResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 博客-我的信息管理Service
 */
public interface BLogsMyInfoManageService {

    /**
     * 显示我的信息首页
     *
     * @param request
     * @return
     */
    SystemResult index(HttpServletRequest request);
}
