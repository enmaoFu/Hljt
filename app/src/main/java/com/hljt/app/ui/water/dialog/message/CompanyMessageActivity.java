package com.hljt.app.ui.water.dialog.message;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.em.banner.Banner;
import com.em.banner.BannerConfig;
import com.em.banner.Transformer;
import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.hljt.app.R;
import com.hljt.app.adapter.CompanyMessageAdapter;
import com.hljt.app.base.BaseAty;
import com.hljt.app.pojo.CompanyMessagePojo;
import com.hljt.app.ui.water.dialog.expert.ChatActivity;
import com.hljt.app.view.BannerImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  应用辅助软件-》事故单位信息activity
 * @date   2017/09/22
 * @author enmaoFu
 */
public class CompanyMessageActivity extends BaseAty{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv_data)
    RecyclerView rv_data;
    @Bind(R.id.banner)
    Banner mBanner;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private CompanyMessageAdapter companyMessageAdapter;
    //数据源
    private List<CompanyMessagePojo> companyMessagePojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_message;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"事故单位信息");

        //设置图片加载集合
        List<String> imageArray = new ArrayList<>();
        imageArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507345669&di=d730210da56769a973b738833c31479f&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160812%2F142c22e9baea444985ada03c2640adc8_th.png");
        imageArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506750858752&di=b12c049580c96b283a18187ad8ca9797&imgtype=0&src=http%3A%2F%2Fpic38.nipic.com%2F20140213%2F15401927_142845097384_2.jpg");
        imageArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506059755376&di=5f0281bd999b73bd19283eabb8442fc0&imgtype=0&src=http%3A%2F%2Fres.co188.com%2Fdata%2Fdrawing%2Fimg100%2F406910397363350.jpg");

        //设置标题集合
        List<String> titleArray = new ArrayList<>();
        titleArray.add("消防疏散与进攻路线图1");
        titleArray.add("消防疏散与进攻路线图2");
        titleArray.add("消防疏散与进攻路线图3");

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置圆形指示器靠右
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        mBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        mBanner.setImages(imageArray);
        //设置标题
        mBanner.setBannerTitles(titleArray);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        //实例化布局管理器
        //mLayoutManager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
        //流式布局2列，垂直方向
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //实例化适配器
        companyMessageAdapter = new CompanyMessageAdapter(R.layout.item_company_message, setData());
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
                companyMessageAdapter.getItem(position).getContent();
                startActivity(CompanyMessageDetailsActivity.class,null);
            }
        });
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        companyMessageAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(companyMessageAdapter);

    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    public List<CompanyMessagePojo> setData(){
        companyMessagePojos = new ArrayList<>();
        CompanyMessagePojo companyMessagePojo1 = new CompanyMessagePojo(R.drawable.b_2,"全景图");
        CompanyMessagePojo companyMessagePojo2 = new CompanyMessagePojo(R.drawable.pmt,"楼层平面图");
        CompanyMessagePojo companyMessagePojo3 = new CompanyMessagePojo(R.drawable.b_4,"实景图");
        CompanyMessagePojo companyMessagePojo4 = new CompanyMessagePojo(R.drawable.b_5,"态势图");
        CompanyMessagePojo companyMessagePojo5 = new CompanyMessagePojo(R.drawable.b_6,"总平面图");
        CompanyMessagePojo companyMessagePojo6 = new CompanyMessagePojo(R.drawable.b_8,"地图");
        CompanyMessagePojo companyMessagePojo7 = new CompanyMessagePojo(R.drawable.b_3,"BIM图");
        companyMessagePojos.add(companyMessagePojo1);
        companyMessagePojos.add(companyMessagePojo2);
        companyMessagePojos.add(companyMessagePojo3);
        companyMessagePojos.add(companyMessagePojo4);
        companyMessagePojos.add(companyMessagePojo5);
        companyMessagePojos.add(companyMessagePojo6);
        companyMessagePojos.add(companyMessagePojo7);
        return companyMessagePojos;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        menu.getItem(0).setIcon(R.drawable.sousuo);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

}
