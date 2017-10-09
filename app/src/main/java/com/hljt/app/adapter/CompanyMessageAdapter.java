package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.CompanyMessagePojo;

import java.util.List;

/**
 * @title  应用辅助软件-》事故单位信息适配器
 * @date   2017/09/22
 * @author enmaoFu
 */
public class CompanyMessageAdapter extends BaseQuickAdapter<CompanyMessagePojo,BaseViewHolder>{

    public CompanyMessageAdapter(int layoutResId, List<CompanyMessagePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CompanyMessagePojo item) {
        helper.setImageResource(R.id.img,item.getImg());
        helper.setText(R.id.content,item.getContent());
    }
}
