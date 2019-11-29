package org.king2.blogs.service.impl;

import org.king2.blogs.appoint.BLogsIndexAppoint;
import org.king2.blogs.appoint.BLogsUserServiceAppoint;
import org.king2.blogs.dto.BLogsIndexDto;
import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.service.BLogsIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * =======================================================
 * 说明:    博客首页Service 实现类
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.24  		创建
 * <p>
 * =======================================================
 */
@Service
public class BLogsIndexServiceImpl implements BLogsIndexService {

    // 注入博客首页委托类
    @Autowired
    private BLogsIndexAppoint bLogsIndexAppoint;

    /**
     * 博客首页数据的查询
     *
     * @param request
     * @return
     */
    @Override
    public SystemResult index(HttpServletRequest request) throws Exception {

        // 从request中取出用户的信息
        Object user = request.getAttribute("user");
        // 创建返回的Dto信息
        BLogsIndexDto indexDto = new BLogsIndexDto();
        if (user == null) {
            indexDto.setLogin(false);
        } else {
            // 这边说明是登录了， 我们需要进行其他的处理
            BLogsUser logsUser = (BLogsUser) user;
            BLogsUserServiceAppoint.writeUserInfo(indexDto, logsUser);
            indexDto.setLogin(true);
        }

        // 公共处理。。。
        // 比如首页的内容  推荐的博客。。。。
        bLogsIndexAppoint.commonHandle(indexDto);

        return new SystemResult(indexDto);
    }
}
