package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.ChatGroupingPojo;

import java.util.List;

/**
 * @title  专家在线-》列表适配器
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatGroupingAdapter extends BaseQuickAdapter<ChatGroupingPojo,BaseViewHolder>{

    public ChatGroupingAdapter(int layoutResId, List<ChatGroupingPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatGroupingPojo item) {
        helper.setImageByUrl(R.id.head,item.getHead());
        helper.setText(R.id.name,item.getName());
    }
}
