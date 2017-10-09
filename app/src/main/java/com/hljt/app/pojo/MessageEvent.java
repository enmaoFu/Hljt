package com.hljt.app.pojo;

/**
 * @title  事件总线实体类
 * @date   2017/09/28
 * @author enmaoFu
 */
public class MessageEvent {

    private String id;

    private String name;

    private String img;

    private String content;

    public MessageEvent(String id, String name, String img, String content) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
