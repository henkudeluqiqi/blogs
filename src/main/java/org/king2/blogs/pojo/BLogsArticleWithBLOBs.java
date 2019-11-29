package org.king2.blogs.pojo;

public class BLogsArticleWithBLOBs extends BLogsArticle {
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