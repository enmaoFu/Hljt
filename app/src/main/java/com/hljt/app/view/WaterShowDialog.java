package com.hljt.app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.em.baseframe.view.dialog.NiftyDialogNullDataBuilder;
import com.hljt.app.R;
/**
 * @title  首页弹框类
 */
public class WaterShowDialog extends NiftyDialogNullDataBuilder {

    private ImageView closeImg;
    private LinearLayout sgdw;
    private LinearLayout txcs;
    private LinearLayout fzrj;
    private LinearLayout zjzx;
    private LinearLayout tsyc;
    private LinearLayout yyjs;

    public WaterShowDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.water_show_dialog, null, false);

        closeImg = (ImageView)view.findViewById(R.id.close);
        sgdw = (LinearLayout)view.findViewById(R.id.sgdw);
        txcs = (LinearLayout)view.findViewById(R.id.txcs);
        fzrj = (LinearLayout)view.findViewById(R.id.fzrj);
        zjzx = (LinearLayout)view.findViewById(R.id.zjzx);
        tsyc = (LinearLayout)view.findViewById(R.id.tsyc);
        yyjs = (LinearLayout)view.findViewById(R.id.yyjs);

        setND_AddCustomView(view);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public WaterShowDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 监听应用计算
     * @param click
     * @return
     */
    public WaterShowDialog setClickYYJS(View.OnClickListener click){
        yyjs.setOnClickListener(click);
        return this;
    }

    /**
     * 监听灾情态势预测
     * @param click
     * @return
     */
    public WaterShowDialog setClickTSYC(View.OnClickListener click){
        tsyc.setOnClickListener(click);
        return this;
    }

    /**
     * 监听专家在线
     * @param click
     * @return
     */
    public WaterShowDialog setClickZJZX(View.OnClickListener click){
        zjzx.setOnClickListener(click);
        return this;
    }

    /**
     * 监听应用辅助软件
     * @param click
     * @return
     */
    public WaterShowDialog setClickFZRJ(View.OnClickListener click){
        fzrj.setOnClickListener(click);
        return this;
    }

    /**
     * 监听事故单位信息
     * @param click
     * @return
     */
    public WaterShowDialog setClickSGDW(View.OnClickListener click){
        sgdw.setOnClickListener(click);
        return this;
    }

    /**
     * 监听现场图像传输
     * @param click
     * @return
     */
    public WaterShowDialog setClickTXCS(View.OnClickListener click){
        txcs.setOnClickListener(click);
        return this;
    }

    /**
     * 关闭按钮
     *
     * @param click
     * @return
     */
    public WaterShowDialog setDismissClick(View.OnClickListener click) {
        closeImg.setOnClickListener(click);
        return this;
    }

}
