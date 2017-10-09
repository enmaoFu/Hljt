package com.hljt.app.ui.water;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.hljt.app.R;
import com.hljt.app.adapter.MoreNavigationBarAdapter;
import com.hljt.app.base.BaseAty;
import com.hljt.app.pojo.MoreNavigationBarPojo;
import com.hljt.app.ui.water.dialog.expert.ChatActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  更多导航activity
 * @date   2017/09/21
 * @author enmaoFu
 */
public class MoreNavigationBarActivity extends BaseAty{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv_data)
    RecyclerView rv_data;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private MoreNavigationBarAdapter moreNavigationBarAdapter;
    //数据源
    private List<MoreNavigationBarPojo> moreNavigationBarPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_water_more;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"导航");

        //实例化布局管理器
        //mLayoutManager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
        // 竖直方向的网格样式，每行3个Item
        mLayoutManager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
        //实例化适配器
        moreNavigationBarAdapter = new MoreNavigationBarAdapter(R.layout.itme_more_navigation_bar, setData());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*rv_data.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //设置item点击事件
        rv_data.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("测试");
            }
        });
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        moreNavigationBarAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(moreNavigationBarAdapter);

    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    /**
     * 设置数据
     * @return
     */
    public List<MoreNavigationBarPojo> setData(){
        moreNavigationBarPojos = new ArrayList<>();
        MoreNavigationBarPojo moreNavigationBarPojo1 = new MoreNavigationBarPojo(R.drawable.xifang_1,"消防地理信息");
        MoreNavigationBarPojo moreNavigationBarPojo2 = new MoreNavigationBarPojo(R.drawable.xifang_2,"消防执勤实力");
        MoreNavigationBarPojo moreNavigationBarPojo3 = new MoreNavigationBarPojo(R.drawable.xifang_3,"消防力量调度");
        MoreNavigationBarPojo moreNavigationBarPojo4 = new MoreNavigationBarPojo(R.drawable.xifang_4,"参战力量管理");
        MoreNavigationBarPojo moreNavigationBarPojo5 = new MoreNavigationBarPojo(R.drawable.xifang_5,"作战辅助决策");
        MoreNavigationBarPojo moreNavigationBarPojo6 = new MoreNavigationBarPojo(R.drawable.xifang_6,"实时信息传输");
        MoreNavigationBarPojo moreNavigationBarPojo7 = new MoreNavigationBarPojo(R.drawable.xifang_7,"数字预案管理");
        MoreNavigationBarPojo moreNavigationBarPojo8 = new MoreNavigationBarPojo(R.drawable.xifang_8,"战评总结管理");
        moreNavigationBarPojos.add(moreNavigationBarPojo1);
        moreNavigationBarPojos.add(moreNavigationBarPojo2);
        moreNavigationBarPojos.add(moreNavigationBarPojo3);
        moreNavigationBarPojos.add(moreNavigationBarPojo4);
        moreNavigationBarPojos.add(moreNavigationBarPojo5);
        moreNavigationBarPojos.add(moreNavigationBarPojo6);
        moreNavigationBarPojos.add(moreNavigationBarPojo7);
        moreNavigationBarPojos.add(moreNavigationBarPojo8);
        return moreNavigationBarPojos;
    }

}
