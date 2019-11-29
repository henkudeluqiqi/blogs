package org.king2.blogs.appoint;

import org.king2.blogs.dto.BLogsIndexDto;
import org.king2.blogs.enums.UserStateEnum;
import org.king2.blogs.mapper.BLogsUserMapper;
import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * =======================================================
 * 说明:    用户操作的Service委派类
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.21  		创建
 * <p>
 * =======================================================
 */
@Component
public class BLogsUserServiceAppoint {

    // 注入默认的头像信息
    @Value("${USER_DEFAULT_IMAGE}")
    private String USER_DEFAULT_IMAGE;

    // 注入用户Mapper
    @Autowired
    private BLogsUserMapper bLogsUserMapper;

    /**
     * 校验用户名和密码
     *
     * @param userName
     * @param passWord
     * @return
     */
    public static SystemResult checkUserInfo(String userName, String passWord) {

        // 校验用户名
        if (StringUtils.isEmpty(userName) || userName.length() < 7 || userName.length() > 11) {
            return new SystemResult(100, "用户名的长度为7~11位", null);
        }

        // 校验密码
        if (StringUtils.isEmpty(passWord) || passWord.length() < 7) {
            return new SystemResult(100, "密码的长度为7~15位", null);
        }
        return new SystemResult(200);

    }

    /**
     * 写入数据到Dto中
     *
     * @param dto
     * @param user
     */
    public static void writeUserInfo(BLogsIndexDto dto, BLogsUser user) {
        dto.setuImage(user.getuImage());
        dto.setuName(user.getuName());
        dto.setUserName(user.getUserName());
        dto.setPassWord(user.getPassWord());
    }


    /**
     * 校验用户注册的信息，并填充默认的一些数据
     *
     * @param bLogsUser
     */
    public SystemResult checkRegisterInfo(BLogsUser bLogsUser) throws Exception {

        if (bLogsUser == null) {
            return new SystemResult(100, "请填写表单信息");
        }
        // 判断用户名是否属于正常的约束
        // 判断密码是否属于正常的约束
        SystemResult checkUserResult = checkUserInfo(bLogsUser.getUserName(), bLogsUser.getPassWord());
        if (checkUserResult.getStatus() != 200) return checkUserResult;

        // 判断名称是否属于正常的约束
        if (StringUtils.isEmpty(bLogsUser.getuName()) || bLogsUser.getuName().length() > 5
                || bLogsUser.getuName().length() < 1) {
            return new SystemResult(100, "名称的长度为1-5字符");
        }

        // 校验用户名是否存在
        BLogsUser checkUserName = bLogsUserMapper.get(bLogsUser.getUserName());
        if (checkUserName != null) return new SystemResult(100, "用户名已经存在");

        // 填充默认的数据
        bLogsUser.setCreateTime(new Date());
        bLogsUser.setuImage(USER_DEFAULT_IMAGE);
        bLogsUser.setState(UserStateEnum.SUCCESS.getVALUE());
        bLogsUser.setPassWord(MD5Utils.md5(bLogsUser.getPassWord()));

        return new SystemResult("ok");
    }
}
