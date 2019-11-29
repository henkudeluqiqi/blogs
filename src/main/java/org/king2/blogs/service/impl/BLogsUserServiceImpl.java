package org.king2.blogs.service.impl;

import org.king2.blogs.appoint.BLogsUserServiceAppoint;
import org.king2.blogs.dto.BLogsIndexDto;
import org.king2.blogs.mapper.BLogsUserMapper;
import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.service.BLogsUserService;
import org.king2.blogs.utils.CookieUtils;
import org.king2.blogs.utils.JsonUtils;
import org.king2.blogs.utils.MD5Utils;
import org.king2.webkcache.cache.interfaces.impl.ConcurrentWebCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * =======================================================
 * 说明:    用户操作的Service实现类
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.21  		创建
 * <p>
 * =======================================================
 */
@Service
public class BLogsUserServiceImpl implements BLogsUserService {

    // 注入用户Mapper
    @Autowired
    private BLogsUserMapper bLogsUserMapper;

    // 注入WebKingCache缓存
    @Autowired
    private ConcurrentWebCache concurrentWebCache;

    // 用户存在WebKingCache中的Key
    public static final String USER_KEY = "WEB_KING_CACHE_USER_";

    // 创建一把锁
    private static final Lock USER_REGISTER_LOCK = new ReentrantLock(true);

    // 注入我们的委派类
    @Autowired
    private BLogsUserServiceAppoint bLogsUserServiceAppoint;


    /**
     * -----------------------------------------------------
     * 功能:  用户的登录
     * <p>
     * 参数:
     * userName     String      用户名
     * passWord     String      密码
     * <p>
     * 返回: SystemResult         返回登录的结果
     * -----------------------------------------------------
     */
    @Override
    public SystemResult login(String userName, String passWord, HttpServletResponse response,
                              HttpServletRequest request)
            throws Exception {

        // 校验用户的信息
        SystemResult checkUserResult = BLogsUserServiceAppoint.checkUserInfo(userName, passWord);
        if (checkUserResult.getStatus() != 200) return checkUserResult;

        // 通过用户名查询用户信息
        BLogsUser bLogsUser = bLogsUserMapper.get(userName);
        if (bLogsUser == null) return new SystemResult(100, "用户名或密码错误");

        // 判断密码
        if (!bLogsUser.getPassWord().equals(MD5Utils.md5(passWord)))
            return new SystemResult(100, "用户名或密码错误");

        // 将数据存到Dto中
        BLogsIndexDto dto = new BLogsIndexDto();
        BLogsUserServiceAppoint.writeUserInfo(dto, bLogsUser);
        // 我们需要将用户的信息存入到本地客户端一份。
        // 意思就是存到缓存中
        concurrentWebCache.set(USER_KEY + userName, bLogsUser, true);

        // 写回客户端
        CookieUtils.setCookie(request, response, USER_KEY, JsonUtils.objectToJson(dto), 60 * 60 * 24 * 7, true);
        dto.setPassWord(null);

        return new SystemResult(200, "", dto);
    }


    /**
     * -----------------------------------------------------
     * 功能:  用户的注册
     * <p>
     * 参数:
     * bLogsUser     BLogsUser      用户注册的信息
     * <p>
     * 返回: SystemResult         返回注册的结果
     * -----------------------------------------------------
     */
    @Override
    public SystemResult register(BLogsUser bLogsUser) {

        // 打开一把锁
        USER_REGISTER_LOCK.lock();
        try {
            // 校验用户的一些信息，并补充好一些默认信息
            SystemResult systemResult = bLogsUserServiceAppoint.checkRegisterInfo(bLogsUser);
            if (systemResult.getStatus() != 200) return systemResult;

            // 然后就是插入数据了
            bLogsUserMapper.insert(bLogsUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new SystemResult(100, "网络不稳定。");
        } finally {
            // 释放锁
            USER_REGISTER_LOCK.unlock();
        }

        return new SystemResult(200);
    }
}
