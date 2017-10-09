package com.hljt.app.pojo;

/**
 * @author :enmaoFu
 * @title : 首页水源-》更多导航实体类
 * @create :2017/5/9
 */
public class MoreNavigationBarPojo {

    private int img;

    private String text;

    public MoreNavigationBarPojo(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
