package com.hljt.app.adapter;


import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.MoreNavigationBarPojo;

import java.util.List;

/**
 * @author :enmaoFu
 * @title : 首页水源-》更多导航adapter
 * @create :2017/5/9
 */
public class MoreNavigationBarAdapter extends BaseQuickAdapter<MoreNavigationBarPojo,BaseViewHolder> {

    public MoreNavigationBarAdapter(int layoutResId, List<MoreNavigationBarPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreNavigationBarPojo item) {
        helper.setText(R.id.more_text,item.getText());
        helper.setImageResource(R.id.more_img,item.getImg());
    }
}
