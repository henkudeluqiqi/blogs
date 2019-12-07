package org.king2.blogs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.king2.blogs.pojo.BLogsArticleWithBLOBs;
import org.king2.blogs.pojo.BLogsSource;

import java.util.List;

/**
 * 文章来源Mapper
 */
public interface BLogsSourceMapper {

    /**
     * 获取所有的来源
     *
     * @return
     */
    @Select("SELECT * FROM BLogs_source")
    List<BLogsSource> getAll();

    /**
     * 插入文章的信息
     * @param article
     */
    @Insert("INSERT INTO blogs_article (show_image,author_number,blogs_title," +
            "blogs_content,image_lists,if_public,create_time,update_time,blogs_source,read_size," +
            "`like`,retain1) VALUES (#{a.showImage},#{a.authorNumber},#{a.blogsTitle}," +
            "#{a.blogsContent},#{a.imageLists},#{a.ifPublic}" +
            ",#{a.createTime},#{a.updateTime},#{a.blogsSource},#{a.readSize}" +
            ",#{a.like} , #{a.retain1})")
    void insert(@Param("a") BLogsArticleWithBLOBs article);
}
