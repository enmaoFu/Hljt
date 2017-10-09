package com.hljt.app.pojo;

/**
 * @title  应用辅助软件-》事故单位信息实体类
 * @date   2017/09/22
 * @author enmaoFu
 */
public class CompanyMessagePojo {

    private int img;

    private String content;

    public CompanyMessagePojo(int img, String content) {
        this.img = img;
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
