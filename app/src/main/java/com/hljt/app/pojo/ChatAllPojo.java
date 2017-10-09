package com.hljt.app.pojo;

/**
 * @title  专家在线-》所有好友实体类
 * @date   2017/09/27
 * @author enmaoFu
 */
public class ChatAllPojo {

    private String head;

    private String name;

    public ChatAllPojo(String head, String name) {
        this.head = head;
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
