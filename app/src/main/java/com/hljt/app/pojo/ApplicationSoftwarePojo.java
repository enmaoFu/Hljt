package com.hljt.app.pojo;

/**
 * @title  应用辅助软件-》灾害事故处理应用软件实体类
 * @date   2017/09/22
 * @author enmaoFu
 */
public class ApplicationSoftwarePojo {

    private int bgColor;

    private int img;

    protected String text;

    public ApplicationSoftwarePojo(int bgColor, int img, String text) {
        this.bgColor = bgColor;
        this.img = img;
        this.text = text;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
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
