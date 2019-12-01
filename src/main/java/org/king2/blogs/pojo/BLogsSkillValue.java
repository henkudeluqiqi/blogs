package org.king2.blogs.pojo;

import java.util.Date;

public class BLogsSkillValue {
    private Integer skillValueId;

    private Integer skillKeyId;

    private String skillValue;

    private String skillDescContent;

    private String userName;

    private Date createTime;

    private Date updateTime;

    private String retain;

    private String retain2;

    public Integer getSkillValueId() {
        return skillValueId;
    }

    public void setSkillValueId(Integer skillValueId) {
        this.skillValueId = skillValueId;
    }

    public Integer getSkillKeyId() {
        return skillKeyId;
    }

    public void setSkillKeyId(Integer skillKeyId) {
        this.skillKeyId = skillKeyId;
    }

    public String getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(String skillValue) {
        this.skillValue = skillValue;
    }

    public String getSkillDescContent() {
        return skillDescContent;
    }

    public void setSkillDescContent(String skillDescContent) {
        this.skillDescContent = skillDescContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRetain() {
        return retain;
    }

    public void setRetain(String retain) {
        this.retain = retain;
    }

    public String getRetain2() {
        return retain2;
    }

    public void setRetain2(String retain2) {
        this.retain2 = retain2;
    }
}