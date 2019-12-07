package org.king2.blogs.pojo;

import javax.validation.constraints.NotBlank;

public class BLogsArticleWithBLOBs extends BLogsArticle {
    @NotBlank(message = "文章内容不能为空")
    private String blogsContent;

    private String imageLists;

    public String getBlogsContent() {
        return blogsContent;
    }

    public void setBlogsContent(String blogsContent) {
        this.blogsContent = blogsContent;
    }

    public String getImageLists() {
        return imageLists;
    }

    public void setImageLists(String imageLists) {
        this.imageLists = imageLists;
    }
}