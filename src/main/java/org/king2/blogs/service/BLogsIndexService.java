package org.king2.blogs.service;

import org.king2.blogs.result.SystemResult;

import javax.servlet.http.HttpServletRequest;

/**
 * =======================================================
 * 说明:    博客首页Service
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.24  		创建
 * <p>
 * =======================================================
 */
public interface BLogsIndexService {

    /**
     * 博客首页数据的查询
     *
     * @param request
     * @return
     */
    SystemResult index(HttpServletRequest request) throws Exception;
}
