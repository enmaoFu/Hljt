package com.hljt.app.ui.water.dialog.calculation;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.hljt.app.R;
import com.hljt.app.adapter.ApplicationCalculationAdapter;
import com.hljt.app.base.BaseAty;
import com.hljt.app.pojo.ApplicationCalculationPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * @title  应用辅助软件-》应用计算activity
 * @date   2017/09/22
 * @author enmaoFu
 */
public class ApplicationCalculationActivity extends BaseAty{

    @Bind(R.id.bar_fanhui)
    ImageView bar_fanhui;
    @Bind(R.id.bar_text)
    TextView bar_text;
    @Bind(R.id.bar_sousuo)
    ImageView bar_sousuo;
    @Bind(R.id.line_chart)
    LineChartView lcView;

    //图表
    //String[] weeks = {"周一","周二","周三","周四","周五","周六","周日"};//X轴的标注
    //int[] weather = {9,7,6,7,8,6,8};//图表的数据

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    //private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private ApplicationCalculationAdapter applicationCalculationAdapter;
    //数据源
    private List<ApplicationCalculationPojo> applicationCalculationPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_application_calculation;
    }

    @Override
    protected void initData() {

        getAxisPoints();//获取坐标点
        initLineChart();//初始化

    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.bar_fanhui,R.id.bar_text})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.bar_fanhui:
                finish();
                break;
            case R.id.bar_text:
                showPopupWindow(bar_text);
                break;
        }
    }

    /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#26A8B6")).setCubic(false);  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
        line.setFilled(true);//是否填充曲线的面积
        //line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        /*//坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setName("未来几天的天气");  //表格名称
        axisX.setTextSize(7);//设置字体大小
        axisX.setMaxLabelChars(7);  //最多几个X轴坐标
        //axisX.setValues(mAxisValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部*/

        /*Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        axisY.setName("温度");//y轴标注
        axisY.setTextSize(7);//设置字体大小

        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边*/

        //设置行为属性，支持缩放、滑动以及平移
        lcView.setInteractive(true);
        lcView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lcView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lcView.setLineChartData(data);
        lcView.setVisibility(View.VISIBLE);
    }


    /**
     * X 轴的显示
     */
    /*private void getAxisLables(){
        for (int i = 0; i < weeks.length; i++) {
            mAxisValues.add(new AxisValue(i).setLabel(weeks[i]));
        }
    }*/

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(){
        mPointValues.add(new PointValue(1, 5));
        mPointValues.add(new PointValue(2, 7));
        mPointValues.add(new PointValue(4, 7));
        mPointValues.add(new PointValue(6, 13));
    }

    /**
     * 弹出框
     * @param view
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_application_calculation, null, false);

        RecyclerView rv_data = (RecyclerView)contentView.findViewById(R.id.rv_data);

        //实例化布局管理器
        mLayoutManager = new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false);
        //实例化适配器
        applicationCalculationAdapter = new ApplicationCalculationAdapter(R.layout.item_application_calculation, setData());
        //设置布局管理器
        rv_data.setLayoutManager(mLayoutManager);
        //大小不受适配器影响
        rv_data.setHasFixedSize(true);
        //设置加载动画类型
        applicationCalculationAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rv_data.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rv_data.setAdapter(applicationCalculationAdapter);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 设置好参数之后再show
        if (Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            view.getLocationInWindow(a);
            popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + view.getHeight());
        } else {
            popupWindow.showAsDropDown(view);
        }

    }

    /**
     * 设置数据
     * @return
     */
    public List<ApplicationCalculationPojo> setData(){
        applicationCalculationPojos = new ArrayList<>();
        ApplicationCalculationPojo applicationCalculationPojo1 = new ApplicationCalculationPojo(R.drawable.pop_1,"燃烧面积");
        ApplicationCalculationPojo applicationCalculationPojo2 = new ApplicationCalculationPojo(R.drawable.pop_2,"现场车辆供水能力");
        ApplicationCalculationPojo applicationCalculationPojo3 = new ApplicationCalculationPojo(R.drawable.pop_3,"现场供水需求");
        ApplicationCalculationPojo applicationCalculationPojo4 = new ApplicationCalculationPojo(R.drawable.pop_4,"作战能力需求计算");
        ApplicationCalculationPojo applicationCalculationPojo5 = new ApplicationCalculationPojo(R.drawable.pop_5,"供水能力");
        ApplicationCalculationPojo applicationCalculationPojo6 = new ApplicationCalculationPojo(R.drawable.pop_6,"洗消药剂用量");
        ApplicationCalculationPojo applicationCalculationPojo7 = new ApplicationCalculationPojo(R.drawable.pop_7,"参展单位出战能力计算");
        ApplicationCalculationPojo applicationCalculationPojo8 = new ApplicationCalculationPojo(R.drawable.pop_8,"泡沫液供液能力");
        applicationCalculationPojos.add(applicationCalculationPojo1);
        applicationCalculationPojos.add(applicationCalculationPojo2);
        applicationCalculationPojos.add(applicationCalculationPojo3);
        applicationCalculationPojos.add(applicationCalculationPojo4);
        applicationCalculationPojos.add(applicationCalculationPojo5);
        applicationCalculationPojos.add(applicationCalculationPojo6);
        applicationCalculationPojos.add(applicationCalculationPojo7);
        applicationCalculationPojos.add(applicationCalculationPojo8);
        return applicationCalculationPojos;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        menu.getItem(0).setIcon(R.drawable.sousuo);
        return super.onCreateOptionsMenu(menu);
    }

}
