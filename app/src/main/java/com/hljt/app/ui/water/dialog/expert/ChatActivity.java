package com.hljt.app.ui.water.dialog.expert;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.hljt.app.R;
import com.hljt.app.adapter.ChatAdapter;
import com.hljt.app.base.BaseAty;
import com.hljt.app.pojo.ChatPojo;
import com.hljt.app.sql.ConversationPojo;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @title  应用辅助软件-》专家在线-》聊天页面activity
 * @date   2017/09/27
 * @author enmaoFu
 */
public class ChatActivity extends BaseAty implements TextWatcher{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv_data)
    RecyclerView rv_data;
    @Bind(R.id.input_msg)
    EditText inputMessage;
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.more)
    LinearLayout more;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private ChatAdapter chatAdapter;
    //数据源
    private List<ChatPojo> chatPojos;
    //是否已经弹出更多
    private boolean isMore;
    //输入的消息
    private String getInputMessage;
    //接收消息类型
    private static final int TYPE_RECEIVE = 1;
    //发送消息类型
    private static final int TYPE_SEND = 2;
    //有聊天记录
    private static final int YES_RECORD = 3;
    //没有有聊天记录
    private static final int NO_RECORD = 4;
    //接收消息实体类
    private EMMessageBody emMessageBody;
    //消息实体类
    private ChatPojo chatPojo;
    //接收的聊天对象名字
    private String name;
    //数据库
    private ConversationPojo conversationPojo;
    //所有数据
    private List<ConversationPojo> conversationPojos;
    //头像数据
    private String head = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif";

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            //更新UI
            switch (msg.what){
                case 1:
                    //发送消息成功后，将发送消息加入列表
                    chatPojo = new ChatPojo(head, getInputMessage,TYPE_SEND,YES_RECORD);
                    chatAdapter.addData(chatPojo);
                    //发送消息成功后清理输入框
                    inputMessage.setText("");
                    //将recyclerview滚动到最底部
                    rv_data.scrollToPosition(chatAdapter.getItemCount() - 1);

                    /**
                     * 首先获取到数据库里所有的会话记录数据
                     * 然后判断会话记录是否为空
                     * 如果为空就直接将本次会话数据存入数据库
                     * 如果不为空就循环遍历数据库取到的所有会话记录数据，判断已经存在的会话记录和当前会话是否相同。如果相同就跳出判断，如果不相同就把当前会话数据存入数据库
                     * 注意：由于不能在for循环里直接判断处理逻辑，所以定义了一个boolean变量来控制判断
                     */
                    //得到所有数据
                    conversationPojos = DataSupport.findAll(ConversationPojo.class);
                    if(conversationPojos.size() == 0){
                        conversationPojo = new ConversationPojo();
                        conversationPojo.setConversationId("0");
                        conversationPojo.setConversationImg(head);
                        conversationPojo.setConversationName(name);
                        conversationPojo.setConversationContent(getInputMessage);
                        conversationPojo.save();
                    }else{
                        boolean isFind = false;
                        for(int i = 0; i < conversationPojos.size(); i++){
                            Logger.e("数据库获取的："+conversationPojos.get(i).getConversationName() + " 传进的：" + name);
                            if(conversationPojos.get(i).getConversationName().equals(name)){
                                isFind = true;
                                break;
                            }
                        }
                        if(isFind){
                            Logger.e("单聊发送获取---相同");
                            return;
                        }else{
                            Logger.e("单聊发送获取---不相同");
                            conversationPojo = new ConversationPojo();
                            conversationPojo.setConversationId("0");
                            conversationPojo.setConversationImg(head);
                            conversationPojo.setConversationName(name);
                            conversationPojo.setConversationContent(getInputMessage);
                            conversationPojo.save();
                        }
                    }
                    break;
                case 2:
                    showErrorToast("发送消息失败，请重试");
                    break;
                case 3:
                    //接收消息成功后，将发送消息加入列表
                    chatPojo = new ChatPojo(head,
                            emMessageBody.toString().substring(5,emMessageBody.toString().length() - 1),TYPE_RECEIVE,YES_RECORD);
                    chatAdapter.addData(chatPojo);
                    //将recyclerview滚动到最底部
                    rv_data.scrollToPosition(chatAdapter.getItemCount() - 1);

                    //得到所有数据
                    conversationPojos = DataSupport.findAll(ConversationPojo.class);
                    if(conversationPojos.size() == 0){
                        conversationPojo = new ConversationPojo();
                        conversationPojo.setConversationId("0");
                        conversationPojo.setConversationImg(head);
                        conversationPojo.setConversationName(name);
                        conversationPojo.setConversationContent(emMessageBody.toString().substring(5,emMessageBody.toString().length() - 1));
                        conversationPojo.save();
                    }else{
                        boolean isFind = false;
                        for(int i = 0; i < conversationPojos.size(); i++){
                            Logger.e("数据库获取的："+conversationPojos.get(i).getConversationName() + " 传进的：" + name);
                            if(conversationPojos.get(i).getConversationName().equals(name)){
                                isFind = true;
                                break;
                            }
                        }
                        if(isFind){
                            Logger.e("单聊接收获取---相同");
                            return;
                        }else{
                            Logger.e("单聊接收获取---不相同");
                            conversationPojo = new ConversationPojo();
                            conversationPojo.setConversationId("0");
                            conversationPojo.setConversationImg(head);
                            conversationPojo.setConversationName(name);
                            conversationPojo.setConversationContent(emMessageBody.toString().substring(5,emMessageBody.toString().length() - 1));
                            conversationPojo.save();
                        }
                    }
                    break;
            }
        };
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData() {

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");

        initToolbar(mToolbar,name);

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        chatAdapter = new ChatAdapter(R.layout.item_chat, new ArrayList<ChatPojo>());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        chatAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(chatAdapter);
        //将recyclerview滚动到最底部
        rv_data.scrollToPosition(chatAdapter.getItemCount() - 1);
        //监听输入框变化，切换右边按钮的切换
        inputMessage.addTextChangedListener(this);

        getMessageNumber();

    }

    @OnClick({R.id.add})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.add:
                String getAddText = add.getText().toString().trim();
                if(getAddText.length() == 0){
                    if(isMore){
                        /**
                         * 隐藏动画
                         */
                        /*TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                -1.0f);
                        mHiddenAction.setDuration(500);

                        more.startAnimation(mHiddenAction);*/
                        more.setVisibility(View.GONE);
                    }else{
                        /**
                         * 显示动画
                         */
                        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                        mShowAction.setDuration(500);
                        //开始动画
                        more.startAnimation(mShowAction);
                        more.setVisibility(View.VISIBLE);
                        //将recyclerview滚动到最底部
                        rv_data.scrollToPosition(chatAdapter.getItemCount() - 1);
                    }
                    isMore = !isMore;
                }else{
                    getInputMessage = inputMessage.getText().toString().trim();
                    if(getInputMessage.length() == 0){
                        showErrorToast("消息不能为空");
                    }else{
                        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                        EMMessage message = EMMessage.createTxtSendMessage(getInputMessage, name);
                        //注册回调监听
                        message.setMessageStatusCallback(emCallBack);
                        EMClient.getInstance().chatManager().sendMessage(message);
                    }
                }
                break;
        }
    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    /**
     * 获取消息总数
     */
    public void getMessageNumber(){
        //获取消息总数
        EMConversation messageNumber = EMClient.getInstance().chatManager().getConversation(name);
        /**
         * 此处捕获异常是为了避免没有聊天消息（记录）时的空指针异常
         */
        try{
            //获取此会话在本地的所有的消息数量
            int number = messageNumber.getAllMsgCount();
            if(number != 0){
                //获取此conversation当前内存所有的message
                List<EMMessage> messageList = messageNumber.getAllMessages();
                chatPojos = new ArrayList<>();
                for(int i = 0; i < messageList.size(); i++){
                    if(messageList.get(i).direct() == EMMessage.Direct.RECEIVE){
                        chatPojo = new ChatPojo(head,
                                messageList.get(i).getBody().toString().substring(5,messageList.get(i).getBody().toString().length() - 1),TYPE_RECEIVE,YES_RECORD);
                    }else if(messageList.get(i).direct() == EMMessage.Direct.SEND){
                        chatPojo = new ChatPojo(head,
                                messageList.get(i).getBody().toString().substring(5,messageList.get(i).getBody().toString().length() - 1),TYPE_SEND,YES_RECORD);
                    }
                    chatPojos.add(chatPojo);
                }
                chatAdapter.setNewData(chatPojos);
            }
        }catch (Exception e){
            chatPojos = new ArrayList<>();
            chatPojo = new ChatPojo(head,
                    "没有数据",TYPE_SEND,NO_RECORD);
            chatPojos.add(chatPojo);
            chatAdapter.setNewData(chatPojos);
        }
    }

    /**
     * 实时监听输入框
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {

        int number = inputMessage.getText().toString().length();
        if(number > 0){
            add.setText("发送");
            add.setBackground(getDrawable(R.drawable.text_bg_round3));
            add.setPadding(16,8,16,8);
            add.setWidth(200);
            add.setGravity(Gravity.CENTER);
        }else{
            add.setText("");
            add.setBackground(getDrawable(R.drawable.jiahao));
            add.setPadding(0,0,0,0);
            add.setWidth(0);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    //--------------------------------------------------- 环信发送消息回调监听 ---------------------------------------------------
    EMCallBack emCallBack = new EMCallBack() {
        /**
         * 发送消息成功
         */
        @Override
        public void onSuccess() {
            //更新UI方法  1
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
            //修改发送消息成功后的状态
            Logger.e("发送消息成功");
        }

        /**
         * 发送消息失败
         * @param code
         * @param error
         */
        @Override
        public void onError(int code, String error) {
            //更新UI方法  2
            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
            Logger.e("发送消息失败");
        }

        /**
         * 发送中
         * @param progress
         * @param status
         */
        @Override
        public void onProgress(int progress, String status) {

        }
    };

    //--------------------------------------------------- 环信接收消息回调监听 ---------------------------------------------------
    EMMessageListener emMessageListener = new EMMessageListener() {
        /**
         * 收到消息
         * @param messages
         */
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            for(EMMessage msg:messages) {
                emMessageBody = msg.getBody();
                //更新UI方法  3
                Message message = new Message();
                message.what = 3;
                mHandler.sendMessage(message);
                Logger.e("接收消息成功：" + msg.getBody());
            }
            //Logger.e("接收消息成功：" + emMessageBody.toString());
        }

        /**
         * 收到透传消息
         * @param messages
         */
        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {

        }

        /**
         * 收到已读回执
         * @param messages
         */
        @Override
        public void onMessageRead(List<EMMessage> messages) {

        }

        /**
         * 收到已送达回执
         * @param messages
         */
        @Override
        public void onMessageDelivered(List<EMMessage> messages) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        /**
         * 消息状态变动
         * @param message
         * @param change
         */
        @Override
        public void onMessageChanged(EMMessage message, Object change) {

        }
    };

    //--------------------------------------------------- 环信生命周期管理 ---------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        //注册接收消息的监听
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁的时候移除接收消息监听
        EMClient.getInstance().chatManager().removeMessageListener(emMessageListener);
    }
}
