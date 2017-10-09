package com.hljt.app.ui.water.dialog.expert;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.em.baseframe.util.DensityUtils;
import com.hljt.app.R;
import com.hljt.app.base.BaseAty;
import com.hljt.app.base.BaseFgt;
import com.hljt.app.pojo.MessageEvent;
import com.hljt.app.sql.ConversationPojo;
import com.hljt.app.ui.water.dialog.expert.all.ChatAllFgt;
import com.hljt.app.ui.water.dialog.expert.grouping.ChatGroupingFgt;
import com.hljt.app.ui.water.dialog.expert.message.ChatMessageFgt;
import com.hljt.app.ui.water.dialog.expert.video.ChatVideoFgt;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.adapter.EMAGroup;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @title  应用辅助软件-》专家在线activity
 * @date   2017/09/26
 * @author enmaoFu
 */
@RuntimePermissions
public class ExpertOnLineActivity extends BaseAty implements EMCallBack {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_layout)
    TabLayout mTab;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private List<BaseFgt> mFragments;
    private List<String> mTabsString;

    //数据库
    private SQLiteDatabase db;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            //更新UI
            switch (msg.what){
                case 1:

                    initToolbar(mToolbar,"专家在线");

                    mFragments = new ArrayList<>();
                    mTabsString = new ArrayList<>();
                    mTabsString.add("");
                    mTabsString.add("");
                    mTabsString.add("");
                    mTabsString.add("");

                    mFragments.add(new ChatAllFgt());
                    mFragments.add(new ChatMessageFgt());
                    mFragments.add(new ChatGroupingFgt());
                    mFragments.add(new ChatVideoFgt());

                    //设置间隔
                    LinearLayout linearLayout = (LinearLayout) mTab.getChildAt(0);
                    linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                    /*linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                       R.drawable.tab_divider_vertical));*/
                    linearLayout.setDividerPadding(DensityUtils.dp2px(ExpertOnLineActivity.this,15));

                    //普通在activity里面嵌套fragment里这样写
                    ExpertOnLineActivity.pageAdapter pageAdapter = new ExpertOnLineActivity.pageAdapter(ExpertOnLineActivity.this.getSupportFragmentManager());
                    //在fragment里嵌套fragment的时候这样写
                    //pageAdapter pageAdapter = new pageAdapter(getChildFragmentManager());
                    mViewPager.setOffscreenPageLimit(4);
                    mViewPager.setAdapter(pageAdapter);

                    mTab.setupWithViewPager(mViewPager);

                    mTab.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.all));
                    mTab.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.message));
                    mTab.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.grouping));
                    mTab.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.zj_video));

                    if (DensityUtils.getScreenWidth(ExpertOnLineActivity.this)<=700){

                    }else {
                        mTab.setTabMode(TabLayout.MODE_FIXED);
                    }

                    dismissLoadingDialog();

                    break;

                case 2:
                    dismissLoadingDialog();
                    finish();
                    showErrorToast("加载聊天室失败，请检查网络并重试");
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert_on_line;
    }

    @Override
    protected void initData() {

        //获得数据库实例
        db = Connector.getDatabase();

        //当android系统小于5.0的时候直接定位显示，不用动态申请权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showLoadingDialog(null);
            EMClient.getInstance().login("zhangsan","123456",this);
        }else{
            ExpertOnLineActivityPermissionsDispatcher.ApplySuccessWithCheck(this);
        }
    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    //--------------------------------------------------- 环信登录回调 ---------------------------------------------------
    @Override
    public void onSuccess() {
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();

        //更新UI方法  1
        Message message = new Message();
        message.what = 1;
        mHandler.sendMessage(message);

        Logger.v("--------------------------------------------------登录聊天服务器成功！");
    }

    @Override
    public void onError(int code, String error) {
        //更新UI方法  2
        Message message = new Message();
        message.what = 2;
        mHandler.sendMessage(message);
        Logger.v("--------------------------------------------------登录聊天服务器失败：" + code + " " + error);
    }

    @Override
    public void onProgress(int progress, String status) {

    }

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

        /**
         * 退出登录
         */
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                Logger.v("--------------------------------------------------退出登录成功！");
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(int code, String message) {
                Logger.v("--------------------------------------------------退出登录失败：" + code + " " + message);
            }
        });

        //关闭数据库
        db.close();
    }

    //--------------------------------------------------- 读写权限的申请 ---------------------------------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        ExpertOnLineActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 申请权限成功时
     */
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void ApplySuccess() {
        showLoadingDialog(null);
        EMClient.getInstance().login("zhangsan","123456",this);
    }

    /**
     * 申请权限告诉用户原因时
     * @param request
     */
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationaleForMap(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开读写权限", request);
    }

    /**
     * 申请权限被拒绝时
     *
     */
    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onMapDenied() {
        Toast.makeText(this,"你拒绝了权限，该功能不可用",Toast.LENGTH_LONG).show();
    }

    /**
     * 申请权限被拒绝并勾选不再提醒时
     */
    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onMapNeverAskAgain() {
        AskForPermission();
    }

    /**
     * 告知用户具体需要权限的原因
     * @param messageResId
     * @param request
     */
    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();//请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    /**
     * 被拒绝并且不再提醒,提示用户去设置界面重新打开权限
     */
    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("当前应用缺少读写权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + ExpertOnLineActivity.this.getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        menu.getItem(0).setIcon(R.drawable.sousuo);
        return super.onCreateOptionsMenu(menu);
    }

    class pageAdapter extends FragmentStatePagerAdapter {
        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsString.get(position);
        }

    }

    //--------------------------------------------------- 环信接收消息回调监听 ---------------------------------------------------
    EMMessageListener emMessageListener = new EMMessageListener() {
        /**
         * 收到消息
         * @param messages
         */
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            for(EMMessage msg:messages) {
                /*if(msg.getChatType() == EMMessage.ChatType.Chat){
                    EventBus.getDefault().post(new MessageEvent("0",msg.getUserName(),
                            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif",
                            msg.getBody().toString().substring(5,msg.getBody().toString().length() - 1)));
                }else if(msg.getChatType() == EMMessage.ChatType.GroupChat){
                    EventBus.getDefault().post(new MessageEvent(msg.getUserName(),msg.getUserName(),
                            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507083360&di=681541dec85bd70ec122584074d6eb92&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.dxs518.cn%2Fimagesfiles%2Farticle%2F615%2F201628%2F12_xfny0___jpg.gif",
                            msg.getBody().toString().substring(5,msg.getBody().toString().length() - 1)));
                }*/
                Logger.e("聊天主Activity里接收消息成功：" + msg.getUserName() + "---" + msg.getFrom() + "---" + msg.getTo() + "---" + msg.getMsgId());
            }
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

}
