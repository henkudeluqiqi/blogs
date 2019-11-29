package org.king2.blogs.mapper;

import org.apache.ibatis.annotations.Select;
import org.king2.blogs.pojo.BLogsArticleWithBLOBs;

import java.util.List;

/**
 * 博客首页的Mapper
 *
 * @author 鹿七七sama
 */
public interface BLogsIndexMapper {

    /**
     * 查询出阅读数量最高的文章信息
     *
     * @return
     */
    @Select("SELECT * FROM BLogs_article WHERE retain1 = 1 ORDER BY read_size DESC LIMIT 0 , 3")
    List<BLogsArticleWithBLOBs> getArticle();

}
