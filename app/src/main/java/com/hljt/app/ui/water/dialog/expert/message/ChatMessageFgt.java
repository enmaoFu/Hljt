package com.hljt.app.ui.water.dialog.expert.message;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.hljt.app.R;
import com.hljt.app.adapter.ChatMessageAdapter;
import com.hljt.app.base.BaseFgt;
import com.hljt.app.pojo.ChatMessagePojo;
import com.hljt.app.pojo.MessageEvent;
import com.hljt.app.sql.ConversationPojo;
import com.hljt.app.ui.water.dialog.expert.ChatActivity;
import com.hljt.app.ui.water.dialog.expert.ChatGroupActivity;
import com.hyphenate.chat.EMConversation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @title  专家在线-》会话消息Fgt
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatMessageFgt extends BaseFgt{

    @Bind(R.id.rv_data)
    RecyclerView rv_data;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private ChatMessageAdapter chatMessageAdapter;
    //数据源
    private List<ChatMessagePojo> chatMessagePojos;
    private Map<String, EMConversation> conversations;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_message;
    }

    @Override
    protected void initData() {

        //注册EventBus
        EventBus.getDefault().register(this);

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        chatMessageAdapter = new ChatMessageAdapter(R.layout.item_char_message, new ArrayList<ChatMessagePojo>());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //设置item点击事件
        rv_data.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String name = chatMessageAdapter.getItem(position).getName();
                String id = chatMessageAdapter.getItem(position).getId();
                Bundle bundle = new Bundle();
                if(id.equals("0")){
                    bundle.putString("name",name);
                    startActivity(ChatActivity.class,bundle);
                }else{
                    bundle.putString("name",name);
                    bundle.putString("id",id);
                    startActivity(ChatGroupActivity.class,bundle);
                }
            }
        });
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        chatMessageAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(chatMessageAdapter);

        getConversation();

    }

    /**
     * 获取所有会话
     */
    public void getConversation(){

        //获取数据库里所有的缓存会话
        List<ConversationPojo> conversationPojos = DataSupport.findAll(ConversationPojo.class);

        //往recyclerview里添加数据
        chatMessagePojos = new ArrayList<>();
        ChatMessagePojo chatMessagePojo = null;

        for(ConversationPojo cps:conversationPojos){
            chatMessagePojo = new ChatMessagePojo(cps.getConversationId(),cps.getConversationImg(),cps.getConversationName(),cps.getConversationContent());
            chatMessagePojos.add(chatMessagePojo);
        }
        chatMessageAdapter.setNewData(chatMessagePojos);

        /*List<ChatMessageNumber> getConversationMsg = new ArrayList<>();
        ChatMessageNumber chatMessageNumber = null;

        //获取所有会话
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        //根据群组ID从服务器获取群组基本信息

        //遍历会话的Map集合，把其中会话的名字和内容添加到List集合里面
        for(Map.Entry<String, EMConversation> entry : conversations.entrySet()) {
            for(EMMessage cms:entry.getValue().getAllMessages()){
                chatMessageNumber = new ChatMessageNumber(cms.getUserName(),cms.getBody().toString());
                getConversationMsg.add(chatMessageNumber);
            }
        }

        chatMessagePojos = new ArrayList<>();
        ChatMessagePojo chatMessagePojo = null;

        //遍历List集合，给recyclerview添加数据
        for(int i = 0; i < getConversationMsg.size(); i++){
            chatMessagePojo = new ChatMessagePojo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif"
                    ,getConversationMsg.get(i).getName(),
                    getConversationMsg.get(i).getContent().toString().substring(5,getConversationMsg.get(i).getContent().toString().length() - 1));
            chatMessagePojos.add(chatMessagePojo);
        }
        chatMessageAdapter.setNewData(chatMessagePojos);*/
    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.delete})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.delete:
                DataSupport.deleteAll(ConversationPojo.class);
                break;
        }
    }

    //默认调用方式，在调用post方法的线程执行，避免了线程切换，性能开销最少
    // ThreadMode is optional here
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessage(MessageEvent event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
    }

    /**
     * 内部类，是List会话集合的实体类
     */
    /*class ChatMessageNumber{

        private String name;

        private String content;

        public ChatMessageNumber(String name, String content) {
            this.name = name;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }*/
}
