package com.hljt.app.pojo;

/**
 * @title  专家在线-》聊天消息实体类
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatMessagePojo {

    private String id;

    private String img;

    private String name;

    private String message;

    public ChatMessagePojo(String id, String img, String name, String message) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
