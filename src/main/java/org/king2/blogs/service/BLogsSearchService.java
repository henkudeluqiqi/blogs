package org.king2.blogs.service;

import org.king2.blogs.result.SystemResult;

/**
 * =======================================================
 * 说明:    博客搜索Service
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.25  		创建
 * <p>
 * =======================================================
 */
public interface BLogsSearchService {

    /**
     * 查询文章根据条件
     *
     * @param query
     * @return
     */
    SystemResult getByQuery(String query);
}
