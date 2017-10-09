package com.hljt.app.pojo;

/**
 * @title  专家在线-》列表实体类
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatGroupingPojo {

    private String id;

    private String head;

    private String name;

    public ChatGroupingPojo(String id, String head, String name) {
        this.id = id;
        this.head = head;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
