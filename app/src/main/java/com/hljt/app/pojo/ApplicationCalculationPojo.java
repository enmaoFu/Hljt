package com.hljt.app.pojo;

/**
 * @title  应用辅助软件-》应用计算弹框实体类
 * @date   2017/09/22
 * @author enmaoFu
 */
public class ApplicationCalculationPojo {

    private int img;

    private String text;

    public ApplicationCalculationPojo(int img, String text) {
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
