package com.hljt.app.ui.water.dialog.transmission.dwmonitor;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.hljt.app.R;
import com.hljt.app.adapter.UnitMonitorAdapter;
import com.hljt.app.base.BaseFgt;
import com.hljt.app.pojo.UnitMonitorPojo;
import com.hljt.app.ui.water.VideoActivity;
import com.hljt.app.ui.water.dialog.expert.ChatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  现场图像传输-》单位监控Fgt
 * @date   2017/09/21
 * @author enmaoFu
 */
public class UnitMonitoringFgt extends BaseFgt{

    @Bind(R.id.rv_data)
    RecyclerView rv_data;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private UnitMonitorAdapter unitMonitorAdapter;
    //数据源
    private List<UnitMonitorPojo> unitMonitorPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dw_monitor;
    }

    @Override
    protected void initData() {

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        unitMonitorAdapter = new UnitMonitorAdapter(R.layout.itme_dw_monitor, setData());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*mTekeRecyclerview.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //设置item点击事件
        rv_data.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(VideoActivity.class,null);
            }
        });
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        unitMonitorAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(unitMonitorAdapter);

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
    }

    @Override
    protected boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {

    }

    /**
     * 设置数据
     * @return
     */
    public List<UnitMonitorPojo> setData(){
        unitMonitorPojos = new ArrayList<>();
        UnitMonitorPojo unitMonitorPojo1 = new UnitMonitorPojo(R.drawable.danweojiankong);
        UnitMonitorPojo unitMonitorPojo2 = new UnitMonitorPojo(R.drawable.chezaishiping);
        UnitMonitorPojo unitMonitorPojo3 = new UnitMonitorPojo(R.drawable.danbingtuchuan);
        UnitMonitorPojo unitMonitorPojo4 = new UnitMonitorPojo(R.drawable.gaokongtiaowang);
        UnitMonitorPojo unitMonitorPojo5 = new UnitMonitorPojo(R.drawable.quntongshiping);
        UnitMonitorPojo unitMonitorPojo6 = new UnitMonitorPojo(R.drawable.neibujiankong);
        UnitMonitorPojo unitMonitorPojo7 = new UnitMonitorPojo(R.drawable.xiaofangkongzhi);
        UnitMonitorPojo unitMonitorPojo8 = new UnitMonitorPojo(R.drawable.daolujiank);
        UnitMonitorPojo unitMonitorPojo9 = new UnitMonitorPojo(R.drawable.gaokongtiaowang1);
        unitMonitorPojos.add(unitMonitorPojo1);
        unitMonitorPojos.add(unitMonitorPojo2);
        unitMonitorPojos.add(unitMonitorPojo3);
        unitMonitorPojos.add(unitMonitorPojo4);
        unitMonitorPojos.add(unitMonitorPojo5);
        unitMonitorPojos.add(unitMonitorPojo6);
        unitMonitorPojos.add(unitMonitorPojo7);
        unitMonitorPojos.add(unitMonitorPojo8);
        unitMonitorPojos.add(unitMonitorPojo9);
        return unitMonitorPojos;
    }

}
