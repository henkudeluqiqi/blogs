package org.king2.blogs.appoint;

import org.king2.blogs.dto.BLogsIndexDto;
import org.king2.blogs.mapper.BLogsIndexMapper;
import org.king2.blogs.pojo.BLogsArticleWithBLOBs;
import org.king2.webkcache.cache.interfaces.WebKingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 博客首页的委托类
 */
@Component
public class BLogsIndexAppoint {

    // 注入文章Mapper
    @Autowired
    private BLogsIndexMapper bLogsIndexMapper;

    // 注入WebKingCache
    @Autowired
    private WebKingCache webKingCache;

    // 文章信息存在缓存中的KEY
    public static final String ART_CACHE_KEY = "ART_CACHE_KEY";

    /**
     * 首页显示的公共信息
     *
     * @param indexDto
     */
    public void commonHandle(BLogsIndexDto indexDto) throws Exception {

        // 查询出文章的信息
        getArticle(indexDto);
    }

    /**
     * 查询出文章的信息
     *
     * @return
     */
    public void getArticle(BLogsIndexDto indexDto) throws Exception {

        // 加锁 保证当前只有一份数据存入缓存中。
        synchronized (ART_CACHE_KEY) {
            // 判断内存中是否存在文章信息
            Object cacheData = webKingCache.get(ART_CACHE_KEY);
            if (cacheData == null) {
                // 缓存中没有数据嘛 需要去数据库查询
                List<BLogsArticleWithBLOBs> article = bLogsIndexMapper.getArticle();
                // 查询出来以后  存入缓存中
                webKingCache.set(ART_CACHE_KEY, article, true);
                cacheData = article;
            }

            // 然后从缓存中取出数据
            indexDto.setArticleWithBLOBs((List<BLogsArticleWithBLOBs>) cacheData);

            // 其实这时候 同志们就有点疑问了？？？
            // 那啥时候清空缓存中的数据啊？？？
            // TODO 写个定时器 每天凌晨会清空一下文章的缓存
        }

    }
}
