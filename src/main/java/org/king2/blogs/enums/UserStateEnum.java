package org.king2.blogs.enums;

/**
 * 用户状态枚举类
 */
public enum UserStateEnum {

    SUCCESS("正常状态", 1),
    ERROR("异常状态", 2);

    UserStateEnum(String KEY, Integer VALUE) {
        this.KEY = KEY;
        this.VALUE = VALUE;
    }

    private String KEY;
    private Integer VALUE;

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public Integer getVALUE() {
        return VALUE;
    }

    public void setVALUE(Integer VALUE) {
        this.VALUE = VALUE;
    }
}
