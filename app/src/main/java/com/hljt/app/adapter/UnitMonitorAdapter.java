package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.UnitMonitorPojo;

import java.util.List;

/**
 * @title  现场图像传输-》单位监控适配器
 * @date   2017/09/21
 * @author enmaoFu
 */
public class UnitMonitorAdapter extends BaseQuickAdapter<UnitMonitorPojo,BaseViewHolder> {

    public UnitMonitorAdapter(int layoutResId, List<UnitMonitorPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitMonitorPojo item) {
        helper.setBackgroundRes(R.id.re_bg,item.getImg());
    }
}
