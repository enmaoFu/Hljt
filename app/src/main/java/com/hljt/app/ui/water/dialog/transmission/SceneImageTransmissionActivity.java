package com.hljt.app.ui.water.dialog.transmission;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;

import com.em.baseframe.util.DensityUtils;
import com.hljt.app.R;
import com.hljt.app.base.BaseAty;
import com.hljt.app.base.BaseFgt;
import com.hljt.app.ui.water.dialog.transmission.czvideo.VehicleVideoFgt;
import com.hljt.app.ui.water.dialog.transmission.dbimage.SingleSoldierImageFgt;
import com.hljt.app.ui.water.dialog.transmission.dwmonitor.UnitMonitoringFgt;
import com.hljt.app.ui.water.dialog.transmission.gklookout.HighSeeFgt;
import com.hljt.app.ui.water.dialog.transmission.nbmonitor.InsideMonitorFgt;
import com.hljt.app.ui.water.dialog.transmission.xfmonitor.FireControlMonitorFgt;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  应用辅助软件-》现场图像传输activity
 * @date   2017/09/22
 * @author enmaoFu
 */
public class SceneImageTransmissionActivity extends BaseAty{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_layout)
    TabLayout mTab;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private List<BaseFgt> mFragments;
    private List<String> mTabsString;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scene_image_transmission;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"现场图像传输");

        mFragments = new ArrayList<>();
        mTabsString = new ArrayList<>();
        mTabsString.add("单位监控");
        mTabsString.add("车载视频");
        mTabsString.add("高空瞭望");
        mTabsString.add("单兵图传");
        mTabsString.add("内部监控");
        mTabsString.add("消防控制室监控");

        mFragments.add(new UnitMonitoringFgt());
        mFragments.add(new VehicleVideoFgt());
        mFragments.add(new HighSeeFgt());
        mFragments.add(new SingleSoldierImageFgt());
        mFragments.add(new InsideMonitorFgt());
        mFragments.add(new FireControlMonitorFgt());

        //设置间隔
        LinearLayout linearLayout = (LinearLayout) mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tab_divider_vertical));
        linearLayout.setDividerPadding(DensityUtils.dp2px(this,15));

        //普通在activity里面嵌套fragment里这样写
        pageAdapter pageAdapter = new pageAdapter(this.getSupportFragmentManager());
        //在fragment里嵌套fragment的时候这样写
        //pageAdapter pageAdapter = new pageAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.setAdapter(pageAdapter);

        mTab.setupWithViewPager(mViewPager);

        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

       /* if (DensityUtils.getScreenWidth(this)<=700){

        }else {
            mTab.setTabMode(TabLayout.MODE_FIXED);
        }*/


    }

    @Override
    public boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {

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

}
