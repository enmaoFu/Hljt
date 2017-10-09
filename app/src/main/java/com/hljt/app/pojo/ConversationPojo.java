package com.hljt.app.pojo;

/**
 * @title  所有会话消息的列表实体类
 * @date   2017/09/28
 * @author enmaoFu
 */
public class ConversationPojo {

    private String id;

    private String chatImg;

    private String chatName;

    private String chatContent;

    public ConversationPojo(String id, String chatImg, String chatName, String chatContent) {
        this.id = id;
        this.chatImg = chatImg;
        this.chatName = chatName;
        this.chatContent = chatContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatImg() {
        return chatImg;
    }

    public void setChatImg(String chatImg) {
        this.chatImg = chatImg;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }
}
