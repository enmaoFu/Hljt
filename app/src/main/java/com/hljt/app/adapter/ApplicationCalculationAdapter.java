package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.ApplicationCalculationPojo;

import java.util.List;

/**
 * @title  应用辅助软件-》应用计算弹框适配器
 * @date   2017/09/22
 * @author enmaoFu
 */
public class ApplicationCalculationAdapter extends BaseQuickAdapter<ApplicationCalculationPojo,BaseViewHolder>{

    public ApplicationCalculationAdapter(int layoutResId, List<ApplicationCalculationPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplicationCalculationPojo item) {
        helper.setImageResource(R.id.pop_img,item.getImg());
        helper.setText(R.id.pop_text,item.getText());
    }
}
