package org.king2.blogs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.king2.blogs.pojo.BLogsUser;

/**
 * =======================================================
 * 说明:    用户操作的Mapper
 * <p>
 * 作者		时间					注释
 * 鹿七		2019.11.21  		创建
 * <p>
 * =======================================================
 */
public interface BLogsUserMapper {

    /**
     * 通过用户名查询用户是否存在
     *
     * @param user_name
     * @return
     */
    @Select("SELECT * FROM bLogs_user WHERE user_name = #{user_name}")
    BLogsUser get(@Param("user_name") String user_name);

    /**
     * @param bLogsUser
     */
    @Insert("INSERT INTO bLogs_user" +
            "(user_name , pass_word , u_name , u_image , create_time , state) " +
            "VALUES (#{userName} , #{passWord} , #{uName} , #{uImage} , #{createTime} , #{state})")
    void insert(BLogsUser bLogsUser);
}
