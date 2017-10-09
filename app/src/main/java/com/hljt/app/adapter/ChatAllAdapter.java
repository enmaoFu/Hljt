package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.ChatAllPojo;

import java.util.List;

/**
 * @title  专家在线-》所有好友适配器
 * @date   2017/09/27
 * @author enmaoFu
 */
public class ChatAllAdapter extends BaseQuickAdapter<ChatAllPojo,BaseViewHolder> {

    public ChatAllAdapter(int layoutResId, List<ChatAllPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatAllPojo item) {
        helper.setImageByUrl(R.id.head,item.getHead());
        helper.setText(R.id.name,item.getName());
    }
}
