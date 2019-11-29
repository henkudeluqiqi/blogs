package org.king2.blogs.dto;

import org.king2.blogs.enums.PojoExBLogsArticle;

import java.util.List;


/**
 * 博客搜索页面需要的数据
 */
public class BLogsSearchDto {

    // 查询的条件
    private String query;

    // 查询的类型
    private List<SearchTypeDto> typeDto;

    // 文章的信息
    private List<PojoExBLogsArticle> articles;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SearchTypeDto> getTypeDto() {
        return typeDto;
    }

    public void setTypeDto(List<SearchTypeDto> typeDto) {
        this.typeDto = typeDto;
    }

    public List<PojoExBLogsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<PojoExBLogsArticle> articles) {
        this.articles = articles;
    }
}
