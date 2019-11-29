package org.king2.blogs.appoint;

import org.king2.blogs.dto.BLogsSearchDto;
import org.king2.blogs.dto.SearchTypeDto;
import org.king2.blogs.enums.BLogsSearchTypeEnum;
import org.king2.blogs.enums.PojoExBLogsArticle;
import org.king2.webkcache.cache.interfaces.WebKingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客搜索的委托类
 */
@Component
public class BLogsSearchAppoint {

    // 注入webKingCache缓存
    @Autowired
    private WebKingCache webKingCache;

    // 类型存在缓存中的Key
    public static final String SEARCH_TYPE_KEY = "SEARCH_TYPE_KEY";

    /**
     * 设置公共的字段
     */
    public void setCommonFiled(String query, BLogsSearchDto bLogsSearchDto,
                               List<PojoExBLogsArticle> bLogsArticles) {

        // 开始设置属性
        bLogsSearchDto.setQuery(query);
        bLogsSearchDto.setArticles(bLogsArticles);

        // 创建一个List用来存放我们的Type
        List<SearchTypeDto> searchTypeDtos = null;

        // 去缓存中获取数据
        try {
            Object o = webKingCache.get(SEARCH_TYPE_KEY);
            if (o == null) {
                // 说明缓存中没有数据  没有数据的话 我们需要从类中获取。
                // 查询类型
                searchTypeDtos = new ArrayList<>();
                for (BLogsSearchTypeEnum value : BLogsSearchTypeEnum.values()) {
                    SearchTypeDto typeDto = new SearchTypeDto();
                    typeDto.setKey(value.getContent());
                    typeDto.setValue(value + "");
                    searchTypeDtos.add(typeDto);
                }

                // 查询完成后 存入缓存中
                webKingCache.set(SEARCH_TYPE_KEY, searchTypeDtos, true);
            } else {
                searchTypeDtos = (List<SearchTypeDto>) o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        bLogsSearchDto.setTypeDto(searchTypeDtos);
    }

}
