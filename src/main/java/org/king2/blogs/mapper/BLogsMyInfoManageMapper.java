package org.king2.blogs.mapper;

import org.apache.ibatis.annotations.Select;
import org.king2.blogs.pojo.BLogsSkillValue;
import org.king2.blogs.pojo.BLogsUserElseInfo;
import org.king2.blogs.pojo.DtoExBLogsSkillValue;

import java.util.List;

/**
 * 博客-我的信息Mapper
 */
@SuppressWarnings("all")
public interface BLogsMyInfoManageMapper {

    /**
     * 通过UserName查询出用户的其他信息
     *
     * @param userName
     * @return
     */
    @Select("SELECT * FROM blogs_user_else_info WHERE retain = #{userName}")
    BLogsUserElseInfo getUserInfoByUserName(String userName);

    /**
     * 通过userName查询用户掌握的技能点
     *
     * @param userName
     * @return
     */
    @Select("SELECT sv.* , bs.skill_key `key` FROM `blogs_skill_value` sv , blogs_skill bs WHERE sv.skill_key_id = bs.skill_id" +
            " AND user_name = #{userName}")
    List<DtoExBLogsSkillValue> getSkillValueByUserName(String userName);
}
