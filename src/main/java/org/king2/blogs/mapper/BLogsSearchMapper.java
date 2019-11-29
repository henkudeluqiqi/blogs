package org.king2.blogs.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.king2.blogs.enums.PojoExBLogsArticle;

import java.util.List;

/**
 * 博客搜索的Mapper
 */
@SuppressWarnings("all")
public interface BLogsSearchMapper {

    /**
     * 根据条件查询文章信息
     *
     * @param query
     * @return
     */
    @Select("SELECT * FROM (\n" +
            "SELECT  a.blogs_id , a.blogs_title , a.update_time , a.`like`, a.author_number , a.read_size  FROM bLogs_article a\n" +
            "WHERE a.blogs_title LIKE '%${query}%' AND a.if_public = 1 AND a.retain1 = 1" +
            ") a1 , (\n" +
            "\tSELECT u_name , user_name FROM blogs_user\n" +
            ") a2 WHERE a1.author_number = a2.user_name")
    List<PojoExBLogsArticle> get(@Param("query") String query);

}
