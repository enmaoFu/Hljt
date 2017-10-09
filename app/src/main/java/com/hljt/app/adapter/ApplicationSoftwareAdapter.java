package com.hljt.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hljt.app.R;
import com.hljt.app.pojo.ApplicationSoftwarePojo;

import java.util.List;

/**
 * @title  应用辅助软件-》灾害事故处理应用软件适配器
 * @date   2017/09/22
 * @author enmaoFu
 */
public class ApplicationSoftwareAdapter extends BaseQuickAdapter<ApplicationSoftwarePojo,BaseViewHolder> {

    public ApplicationSoftwareAdapter(int layoutResId, List<ApplicationSoftwarePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplicationSoftwarePojo item) {
        switch (item.getBgColor()){
            case 1:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg1);
                break;
            case 2:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg2);
                break;
            case 3:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg3);
                break;
            case 4:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg4);
                break;
            case 5:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg5);
                break;
            case 6:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg6);
                break;
            case 7:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg7);
                break;
            case 8:
                helper.setBackgroundRes(R.id.apl_re,R.drawable.appcation_software_round_bg8);
                break;
        }
        helper.setImageResource(R.id.apl_img,item.getImg());
        helper.setText(R.id.apl_text,item.getText());
    }
}
