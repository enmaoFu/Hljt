package com.hljt.app.sql;

import org.litepal.crud.DataSupport;

/**
 * @title  会话数据库表
 * @date   2017/09/28
 * @author enmaoFu
 */
public class ConversationPojo extends DataSupport {

    //会话ID，主要用于群组，单聊全部默认为0
    private String conversationId;

    //会话对象头像
    private String conversationImg;

    //会话对象名称
    private String conversationName;

    //会话对象消息
    private String conversationContent;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationImg() {
        return conversationImg;
    }

    public void setConversationImg(String conversationImg) {
        this.conversationImg = conversationImg;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public String getConversationContent() {
        return conversationContent;
    }

    public void setConversationContent(String conversationContent) {
        this.conversationContent = conversationContent;
    }
}
