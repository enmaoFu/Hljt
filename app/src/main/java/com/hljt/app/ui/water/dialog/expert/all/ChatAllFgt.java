package com.hljt.app.ui.water.dialog.expert.all;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.hljt.app.R;
import com.hljt.app.adapter.ChatAllAdapter;
import com.hljt.app.base.BaseFgt;
import com.hljt.app.pojo.ChatAllPojo;
import com.hljt.app.ui.water.dialog.expert.ChatActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  专家在线-》所有联系人Fgt
 * @date   2017/09/26
 * @author enmaoFu
 */
public class ChatAllFgt extends BaseFgt{

    @Bind(R.id.rv_data)
    RecyclerView rv_data;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private ChatAllAdapter chatAllAdapter;
    //数据源
    private List<ChatAllPojo> chatAllPojos;
    //得到数据的标识
    //private static final String CHAT_LIST_PARAMS = "chat_list_params";
    //得到数据的数据源
    private List<String> nameList;
    //头像数据
    private String head = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif";

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null){
            strList = (List<String>) args.getSerializable(CHAT_LIST_PARAMS);
        }
    }*/

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            //更新UI
            switch (msg.what){
                case 1:
                    chatAllAdapter.setNewData(chatAllPojos);
                    break;
            }
        };
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_all;
    }

    @Override
    protected void initData() {

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        chatAllAdapter = new ChatAllAdapter(R.layout.item_char_all, new ArrayList<ChatAllPojo>());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*rv_data.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor("#70ccd0d3"))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //设置item点击事件
        rv_data.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String name = chatAllAdapter.getItem(position).getName();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                startActivity(ChatActivity.class,bundle);
            }
        });
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        chatAllAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(chatAllAdapter);

        getChatAll();

    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    /**
     * 在这个fragment（子类）里写方法让ExpertOnLineActivity（父类）调用
     */
    /*public static ChatAllFgt setFriendListData(List<String> list){
        ChatAllFgt fragment = new ChatAllFgt();
        Bundle args = new Bundle();
        args.putSerializable(CHAT_LIST_PARAMS, (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }*/

    /*public List<ChatAllPojo> setData(){
        chatAllPojos = new ArrayList<>();
        ChatAllPojo chatAllPojo = null;
        for(int i = 0; i < nameList.size(); i++){
            chatAllPojo = new ChatAllPojo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif",
                    nameList.get(i));
            chatAllPojos.add(chatAllPojo);
        }
        return chatAllPojos;
    }*/

    public void getChatAll(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    chatAllPojos = new ArrayList<>();
                    ChatAllPojo chatAllPojo = null;
                    for(String names:usernames){
                        chatAllPojo = new ChatAllPojo(head,
                                names);
                        chatAllPojos.add(chatAllPojo);
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
