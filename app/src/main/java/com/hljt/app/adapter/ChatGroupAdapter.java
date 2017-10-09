package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.ChatGroupPojo;
import com.hljt.app.pojo.ChatPojo;

import java.util.List;

/**
 * @title  应用辅助软件-》专家在线-》聊天页面 群聊 适配器
 * @date   2017/09/27
 * @author enmaoFu
 */
public class ChatGroupAdapter extends BaseQuickAdapter<ChatGroupPojo,BaseViewHolder>{

    //接收消息类型
    private static final int TYPE_RECEIVE = 1;
    //发送消息类型
    private static final int TYPE_SEND = 2;
    //有聊天记录
    private static final int YES_RECORD = 3;
    //没有有聊天记录
    private static final int NO_RECORD = 4;

    public ChatGroupAdapter(int layoutResId, List<ChatGroupPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatGroupPojo item) {
        helper.setVisible(R.id.receive,false);
        helper.setVisible(R.id.send,false);
        helper.setVisible(R.id.is_msg,false);
        if(item.getIsMsg() == YES_RECORD){
            switch (item.getType()){
                case TYPE_RECEIVE:
                    helper.setVisible(R.id.receive,true);
                    helper.setVisible(R.id.is_msg,false);
                    helper.setImageByUrl(R.id.receive_head,item.getHead());
                    helper.setText(R.id.receive_content,item.getContent());
                    break;
                case TYPE_SEND:
                    helper.setVisible(R.id.send,true);
                    helper.setVisible(R.id.is_msg,false);
                    helper.setImageByUrl(R.id.send_head,item.getHead());
                    helper.setText(R.id.send_content,item.getContent());
                    break;
            }
        }else{
            return;
        }
    }
}
