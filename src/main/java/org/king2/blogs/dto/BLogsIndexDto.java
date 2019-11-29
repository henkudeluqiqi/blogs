package org.king2.blogs.dto;

import org.king2.blogs.pojo.BLogsArticleWithBLOBs;

import java.util.List;

/**
 * 博客首页需要用到的Dto
 *
 * @author 鹿七七sama
 */
public class BLogsIndexDto {

    // 用户称呼
    private String uName;
    // 用户名
    private String userName;
    // 头像
    private String uImage;
    // 密码
    private String passWord;
    // 用户是否登录
    private boolean isLogin;
    // 文章的集合
    private List<BLogsArticleWithBLOBs> articleWithBLOBs;

    public List<BLogsArticleWithBLOBs> getArticleWithBLOBs() {
        return articleWithBLOBs;
    }

    public void setArticleWithBLOBs(List<BLogsArticleWithBLOBs> articleWithBLOBs) {
        this.articleWithBLOBs = articleWithBLOBs;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getuImage() {
        return uImage;
    }

    public void setuImage(String uImage) {
        this.uImage = uImage;
    }
}
