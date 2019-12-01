package org.king2.blogs.service.impl;

import org.king2.blogs.appoint.BLogsMyInfoManageAppoint;
import org.king2.blogs.dto.BLogsUserElseInfoDto;
import org.king2.blogs.enums.BLogSystemState;
import org.king2.blogs.mapper.BLogsMyInfoManageMapper;
import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.pojo.BLogsUserElseInfo;
import org.king2.blogs.pojo.DtoExBLogsSkillValue;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.service.BLogsMyInfoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 博客- 我的信息Service实现类
 */
@Service
public class BLogsMyInfoManageServiceImpl implements BLogsMyInfoManageService {

    // 注入用户其他信息Mapper
    @Autowired
    private BLogsMyInfoManageMapper bLogsMyInfoManageMapper;

    /**
     * 显示我的信息首页
     *
     * @param request
     * @return
     */
    @Override
    public SystemResult index(HttpServletRequest request) {

        // 取出request中的用户信息
        Object user = request.getAttribute("user");
        if (user == null) {
            // 说明用户没有登录  没有登录能进来吗????
            // 其实没有登录的话 是进不到这边来的 ？？？为什么？？？ 因为我们会写一个拦截器
            // 专门拦截那些/acl/**开头的请求，因为这些请求都会查询用户的信息。
            return new SystemResult(BLogSystemState.USER_NO_LOGIN.getVALUE(),
                    BLogSystemState.USER_NO_LOGIN.getKEY());
        }

        // 创建返回的Dto
        BLogsUserElseInfoDto elseInfoDto = new BLogsUserElseInfoDto();

        //  强转用户信息
        BLogsUser userInfo = (BLogsUser) user;

        // 查询该用户的其他信息
        BLogsUserElseInfo userInfoByUserName = bLogsMyInfoManageMapper.getUserInfoByUserName(userInfo.getUserName());

        elseInfoDto.setValues(bLogsMyInfoManageMapper.getSkillValueByUserName(userInfo.getUserName()));
        elseInfoDto.setbLogsUser(userInfo);
        elseInfoDto.setbLogsUserElseInfo(userInfoByUserName);
        // 这时候查询出来会有两种结果 第一种是正常的结果
        // 第二种？？？ 是非正常的。

        /**
         * 存入缓存前的一个处理器
         * 因为我们不知道现在要处理什么
         */
        BLogsMyInfoManageAppoint.addCacheBeforeProcessor(elseInfoDto);

        BLogsMyInfoManageAppoint.returnBeforeProcessor(elseInfoDto);
        return new SystemResult(BLogSystemState.SUCCESS.getVALUE(),
                BLogSystemState.SUCCESS.getKEY(), elseInfoDto);
    }
}
