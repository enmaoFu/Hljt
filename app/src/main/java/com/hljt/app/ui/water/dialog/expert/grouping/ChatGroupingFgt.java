package com.hljt.app.ui.water.dialog.expert.grouping;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.hljt.app.R;
import com.hljt.app.adapter.ChatGroupingAdapter;
import com.hljt.app.base.BaseFgt;
import com.hljt.app.pojo.ChatGroupingPojo;
import com.hljt.app.ui.water.dialog.expert.ChatGroupActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  专家在线-》列表Fgt
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatGroupingFgt extends BaseFgt{

    @Bind(R.id.rv_data)
    RecyclerView rv_data;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private ChatGroupingAdapter chatGroupingAdapter;
    //数据源
    private List<ChatGroupingPojo> chatGroupingPojos;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            //更新UI
            switch (msg.what){
                case 1:
                    chatGroupingAdapter.setNewData(chatGroupingPojos);
                    break;
            }
        };
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_grouping;
    }

    @Override
    protected void initData() {

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        chatGroupingAdapter = new ChatGroupingAdapter(R.layout.item_chat_grouping, new ArrayList<ChatGroupingPojo>());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //设置item点击事件
        rv_data.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String name = chatGroupingAdapter.getItem(position).getName();
                String id = chatGroupingAdapter.getItem(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("id",id);
                startActivity(ChatGroupActivity.class,bundle);
            }
        });
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        chatGroupingAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(chatGroupingAdapter);

        getGroupingList();

    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    /**
     * 获取群组列表
     */
    public void getGroupingList(){
        //从本地加载群组列表 获取当前(内存)用户的所有群组
        //List<EMGroup> grouplist = EMClient.getInstance().groupManager().getAllGroups();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //从服务器获取自己加入的和创建的群组列表，此api获取的群组sdk会自动保存到内存和db。 需异步处理
                    List<EMGroup> grouplist = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
                    chatGroupingPojos = new ArrayList<>();
                    ChatGroupingPojo chatGroupingPojo = null;
                    for(int i = 0; i < grouplist.size(); i++){
                        chatGroupingPojo = new ChatGroupingPojo(grouplist.get(i).getGroupId(),
                                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif",
                                grouplist.get(i).getGroupName());
                        chatGroupingPojos.add(chatGroupingPojo);
                    }
                    //更新UI方法  1
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
