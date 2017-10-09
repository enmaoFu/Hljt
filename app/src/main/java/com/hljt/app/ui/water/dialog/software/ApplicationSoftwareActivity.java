package com.hljt.app.ui.water.dialog.software;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.hljt.app.R;
import com.hljt.app.adapter.ApplicationSoftwareAdapter;
import com.hljt.app.base.BaseAty;
import com.hljt.app.pojo.ApplicationSoftwarePojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @title  应用辅助软件-》灾害事故处理应用软件activity
 * @date   2017/09/22
 * @author enmaoFu
 */
public class ApplicationSoftwareActivity extends BaseAty{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv_data)
    RecyclerView rv_data;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private ApplicationSoftwareAdapter applicationSoftwareAdapter;
    //数据源
    private List<ApplicationSoftwarePojo> applicationSoftwarePojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_application_softwaer;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"灾害事故处理应用软件");

        //实例化布局管理器
        //mLayoutManager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
        // 竖直方向的网格样式，每行3个Item
        mLayoutManager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
        //实例化适配器
        applicationSoftwareAdapter = new ApplicationSoftwareAdapter(R.layout.itme_application_software, setData());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*mTekeRecyclerview.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        applicationSoftwareAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(applicationSoftwareAdapter);

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
    public List<ApplicationSoftwarePojo> setData(){
        applicationSoftwarePojos = new ArrayList<>();
        ApplicationSoftwarePojo applicationSoftwarePojo1 = new ApplicationSoftwarePojo(1,R.drawable.software_img_1,"模糊查询");
        ApplicationSoftwarePojo applicationSoftwarePojo2 = new ApplicationSoftwarePojo(2,R.drawable.software_img_2,"危化品处置规范");
        ApplicationSoftwarePojo applicationSoftwarePojo3 = new ApplicationSoftwarePojo(3,R.drawable.software_img_3,"药剂联储布点");
        ApplicationSoftwarePojo applicationSoftwarePojo4 = new ApplicationSoftwarePojo(4,R.drawable.software_img_4,"类似案件查询");
        ApplicationSoftwarePojo applicationSoftwarePojo5 = new ApplicationSoftwarePojo(5,R.drawable.software_img_5,"事故类型速查");
        ApplicationSoftwarePojo applicationSoftwarePojo6 = new ApplicationSoftwarePojo(6,R.drawable.software_img_6,"现场快速估算");
        ApplicationSoftwarePojo applicationSoftwarePojo7 = new ApplicationSoftwarePojo(7,R.drawable.software_img_7,"实战资料速查");
        ApplicationSoftwarePojo applicationSoftwarePojo8 = new ApplicationSoftwarePojo(8,R.drawable.software_img_8,"MSDS联网检索");
        applicationSoftwarePojos.add(applicationSoftwarePojo1);
        applicationSoftwarePojos.add(applicationSoftwarePojo2);
        applicationSoftwarePojos.add(applicationSoftwarePojo3);
        applicationSoftwarePojos.add(applicationSoftwarePojo4);
        applicationSoftwarePojos.add(applicationSoftwarePojo5);
        applicationSoftwarePojos.add(applicationSoftwarePojo6);
        applicationSoftwarePojos.add(applicationSoftwarePojo7);
        applicationSoftwarePojos.add(applicationSoftwarePojo8);
        return applicationSoftwarePojos;
    }

}
