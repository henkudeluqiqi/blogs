package org.king2.blogs.enums;

public enum BLogsSearchTypeEnum {


    DEFAULT("默认"),
    PIG_LIKE("最高点赞"),
    PIG_READ_SIZE("最高浏览"),
    NEW_CREATE("最新发布");

    private String content;

    BLogsSearchTypeEnum(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
