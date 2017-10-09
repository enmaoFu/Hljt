package com.hljt.app.pojo;

/**
 * @title  应用辅助软件-》专家在线-》聊天页面实体类
 * @date   2017/09/27
 * @author enmaoFu
 */
public class ChatPojo {

    private String head;

    private String content;

    private int type;

    private int isMsg;

    public ChatPojo(String head, String content, int type, int isMsg) {
        this.head = head;
        this.content = content;
        this.type = type;
        this.isMsg = isMsg;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsMsg() {
        return isMsg;
    }

    public void setIsMsg(int isMsg) {
        this.isMsg = isMsg;
    }
}
