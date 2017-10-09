package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.ChatMessagePojo;

import java.util.List;

/**
 * @title  专家在线-》聊天消息适配器
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatMessageAdapter extends BaseQuickAdapter<ChatMessagePojo,BaseViewHolder> {

    public ChatMessageAdapter(int layoutResId, List<ChatMessagePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatMessagePojo item) {
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.message,item.getMessage());
        helper.setImageByUrl(R.id.img,item.getImg());
    }
}
