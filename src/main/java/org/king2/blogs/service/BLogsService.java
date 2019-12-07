package org.king2.blogs.service;

import org.king2.blogs.pojo.BLogsArticle;
import org.king2.blogs.pojo.BLogsArticleWithBLOBs;
import org.king2.blogs.pojo.BLogsSource;
import org.king2.blogs.result.SystemResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 写博客Service
 */
public interface BLogsService {

    /**
     * 初始化确认提交的信息
     */
    List<BLogsSource> initConfirmRelease() throws Exception;


    /**
     * 写博客
     *
     * @param article
     * @param token
     * @param request
     * @return
     */
    SystemResult writeLogs(BLogsArticleWithBLOBs article, String token, HttpServletRequest request)
            throws Exception;
}
