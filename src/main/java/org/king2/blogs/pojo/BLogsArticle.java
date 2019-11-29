package org.king2.blogs.pojo;

import java.util.Date;

public class BLogsArticle {
    private Integer blogsId;

    private String showImage;

    private String authorNumber;

    private String blogsTitle;

    private Integer ifPublic;

    private Date createTime;

    private Date updateTime;

    private Integer blogsSource;

    private String readSize;

    private String like;

    private String retain1;

    private String retain2;

    private String retain3;

    public Integer getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(Integer blogsId) {
        this.blogsId = blogsId;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public String getAuthorNumber() {
        return authorNumber;
    }

    public void setAuthorNumber(String authorNumber) {
        this.authorNumber = authorNumber;
    }

    public String getBlogsTitle() {
        return blogsTitle;
    }

    public void setBlogsTitle(String blogsTitle) {
        this.blogsTitle = blogsTitle;
    }

    public Integer getIfPublic() {
        return ifPublic;
    }

    public void setIfPublic(Integer ifPublic) {
        this.ifPublic = ifPublic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBlogsSource() {
        return blogsSource;
    }

    public void setBlogsSource(Integer blogsSource) {
        this.blogsSource = blogsSource;
    }

    public String getReadSize() {
        return readSize;
    }

    public void setReadSize(String readSize) {
        this.readSize = readSize;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getRetain1() {
        return retain1;
    }

    public void setRetain1(String retain1) {
        this.retain1 = retain1;
    }

    public String getRetain2() {
        return retain2;
    }

    public void setRetain2(String retain2) {
        this.retain2 = retain2;
    }

    public String getRetain3() {
        return retain3;
    }

    public void setRetain3(String retain3) {
        this.retain3 = retain3;
    }
}