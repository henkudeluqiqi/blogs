package org.king2.blogs.dto;

import org.king2.blogs.pojo.BLogsSkillValue;
import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.pojo.BLogsUserElseInfo;
import org.king2.blogs.pojo.DtoExBLogsSkillValue;

import java.util.List;

/**
 * 用户其他信息Dto
 */
public class BLogsUserElseInfoDto {

    /**
     * 用户的主信息
     */
    private BLogsUser bLogsUser;

    /**
     * 用户的其他信息
     */
    private BLogsUserElseInfo bLogsUserElseInfo;

    /**
     * 用户掌握的技能
     *
     * @return
     */
    private List<DtoExBLogsSkillValue> values;


    public List<DtoExBLogsSkillValue> getValues() {
        return values;
    }

    public void setValues(List<DtoExBLogsSkillValue> values) {
        this.values = values;
    }

    public BLogsUser getbLogsUser() {
        return bLogsUser;
    }

    public void setbLogsUser(BLogsUser bLogsUser) {
        this.bLogsUser = bLogsUser;
    }

    public BLogsUserElseInfo getbLogsUserElseInfo() {
        return bLogsUserElseInfo;
    }

    public void setbLogsUserElseInfo(BLogsUserElseInfo bLogsUserElseInfo) {
        this.bLogsUserElseInfo = bLogsUserElseInfo;
    }
}
