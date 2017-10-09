package com.hljt.app;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.em.baseframe.base.BaseActivity;
import com.em.baseframe.util.RetrofitUtils;
import com.hljt.app.http.HttpInterface;
import com.hljt.app.ui.water.MoreNavigationBarActivity;
import com.hljt.app.ui.water.dialog.calculation.ApplicationCalculationActivity;
import com.hljt.app.ui.water.dialog.expert.ExpertOnLineActivity;
import com.hljt.app.ui.water.dialog.message.CompanyMessageActivity;
import com.hljt.app.ui.water.dialog.software.ApplicationSoftwareActivity;
import com.hljt.app.ui.water.dialog.transmission.SceneImageTransmissionActivity;
import com.hljt.app.view.WaterShowDialog;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author enmaoFu
 * @title App主页
 * @date 2017/09/19
 */
@RuntimePermissions
public class MainActivity extends BaseActivity implements EMCallBack {

    @Bind(R.id.bmapView)
    MapView mMapView;
    @Bind(R.id.float_but)
    ImageView float_but;
    @Bind(R.id.water)
    RelativeLayout water;
    @Bind(R.id.vedio)
    RelativeLayout vedio;
    @Bind(R.id.route)
    RelativeLayout route;
    @Bind(R.id.linkage)
    RelativeLayout linkage;
    @Bind(R.id.periphery)
    RelativeLayout periphery;

    /**
     * 判断记录是否选中了当前按钮，默认为没有选false
     */
    private boolean waterFlag;
    private boolean vedioFlag;
    private boolean routeFlag;
    private boolean linkageFlag;
    private boolean peripheryFlag;

    //记录是否有首次按键
    private boolean mBackKeyPressed = false;
    //百度地图
    private BaiduMap mBaiduMap;
    //防止每次定位都重新设置中心点和marker
    private boolean isFirstLocation = true;
    //初始化LocationClient定位类
    private LocationClient mLocationClient = null;
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口
    private BDLocationListener myListener = new MyLocationListener();

    private double lat;
    private double lon;

    /**
     * 解决切换到HomeActivity界面的发生界面黑屏问题
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //当android系统小于5.0的时候直接定位显示，不用动态申请权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            initMap();
        } else {
            MainActivityPermissionsDispatcher.ApplySuccessWithCheck(this);
        }
    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.more, R.id.location, R.id.float_but, R.id.water, R.id.vedio, R.id.route, R.id.linkage, R.id.periphery})
    @Override
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.more:
                startActivity(MoreNavigationBarActivity.class, null);
                break;
            case R.id.location:
                setUserMapCenter();
                break;
            case R.id.float_but:
                showDlalog();
                break;
            case R.id.water:
                if (waterFlag) {
                    water.setBackgroundColor(Color.parseColor("#00090b0e"));
                    showToast("关闭水源");
                } else {
                    water.setBackgroundResource(R.drawable.water_borm_bg_x);
                    showToast("显示水源");
                }
                waterFlag = !waterFlag;
                break;
            case R.id.vedio:
                if (vedioFlag) {
                    vedio.setBackgroundColor(Color.parseColor("#00090b0e"));
                    showToast("关闭视频");
                } else {
                    vedio.setBackgroundResource(R.drawable.water_borm_bg_x);
                    showToast("显示视频");
                }
                vedioFlag = !vedioFlag;
                break;
            case R.id.route:
                if (routeFlag) {
                    route.setBackgroundColor(Color.parseColor("#00090b0e"));
                    showToast("关闭路线");
                } else {
                    route.setBackgroundResource(R.drawable.water_borm_bg_x);
                    showToast("显示路线");
                }
                routeFlag = !routeFlag;
                break;
            case R.id.linkage:
                if (linkageFlag) {
                    linkage.setBackgroundColor(Color.parseColor("#00090b0e"));
                    showToast("关闭联动");
                } else {
                    linkage.setBackgroundResource(R.drawable.water_borm_bg_x);
                    showToast("显示联动");
                }
                linkageFlag = !linkageFlag;
                break;
            case R.id.periphery:
                if (peripheryFlag) {
                    periphery.setBackgroundColor(Color.parseColor("#00090b0e"));
                    showToast("关闭周边");
                } else {
                    periphery.setBackgroundResource(R.drawable.water_borm_bg_x);
                    showToast("显示周边");
                }
                peripheryFlag = !peripheryFlag;
                break;
        }
    }

    /**
     * 显示点击悬浮按钮弹出的对话框
     */
    public void showDlalog() {

        float_but.setVisibility(View.GONE);
        final WaterShowDialog waterShowDialog = new WaterShowDialog(this);
        waterShowDialog.setDismissClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterShowDialog.dismiss();
                float_but.setVisibility(View.VISIBLE);
            }
        }).setClickSGDW(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CompanyMessageActivity.class, null);
            }
        }).setClickTXCS(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SceneImageTransmissionActivity.class, null);
            }
        }).setClickFZRJ(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ApplicationSoftwareActivity.class, null);
            }
        }).setClickZJZX(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ExpertOnLineActivity.class, null);
            }
        }).setClickTSYC(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("灾情态势预测");
            }
        }).setClickYYJS(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ApplicationCalculationActivity.class, null);
            }
        }).show();

    }

    //--------------------------------------------------- 百度地图 ---------------------------------------------------

    /**
     * 初始化百度地图
     */
    public void initMap() {
        //得到地图实例
        mBaiduMap = mMapView.getMap();
        //关闭缩放按钮
        mMapView.showZoomControls(false);
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        //开始定位
        mLocationClient.start();
    }

    /**
     * 配置定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        int span = 5000;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            /*StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }*/

            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            lat = location.getLatitude();
            lon = location.getLongitude();

            //这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                setMarker();
                /*setUserMapCenter();*/
                drawCircle();
                //setPosition2Center(mBaiduMap, location, true);
                setPosition2CenterTest(mBaiduMap,true);
            }

            Logger.v("定位时间：" + location.getTime() + " 定位经度：" + location.getLongitude() + " 定位维度：" + location.getLatitude()
                    + " 定位地址：" + location.getAddrStr());

            /*showToast("定位时间：" + location.getTime() + " 定位经度：" + location.getLongitude() + " 定位维度：" + location.getLatitude()
                    + " 定位地址：" + location.getAddrStr());*/

            doHttp(RetrofitUtils.createApi(HttpInterface.class).navigation(location.getLatitude(), location.getLongitude(), location.getTime()), 0);

        }

    }

    @Override
    public void onSuccess(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        switch (what) {
            case 0:
                Logger.v("成功-----------------------------------------------");
                break;
        }
    }

    /**
     * 添加marker
     * 用于标注地图上N个消火栓
     * 暂时没数据，先注释
     */
    private void setMarker() {
        Logger.v("pcw","setMarker : lat : "+ lat+" lon : " + lon);
        //定义Maker坐标点
        LatLng point = new LatLng(29.275412, 106.279981);
        LatLng point1 = new LatLng(29.275422, 106.279991);
        LatLng point2 = new LatLng(29.273311, 106.278621);
        LatLng point3 = new LatLng(29.278813, 106.285511);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.xiaohuoshuanzhengchang);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        OverlayOptions option1 = new MarkerOptions()
                .position(point1)
                .icon(bitmap);
        OverlayOptions option2 = new MarkerOptions()
                .position(point2)
                .icon(bitmap);
        OverlayOptions option3 = new MarkerOptions()
                .position(point3)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.addOverlay(option1);
        mBaiduMap.addOverlay(option2);
        mBaiduMap.addOverlay(option3);
    }

    /**
     * 设置中心点
     */
    private void setUserMapCenter() {
        Logger.v("pcw", "setUserMapCenter : lat : " + lat + " lon : " + lon);
        LatLng cenpt = new LatLng(lat, lon);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

    }

    /**
     * 设置中心点和添加marker
     *
     * @param map
     * @param bdLocation
     * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        map.setMyLocationData(locData);

        if (isShowLoc) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }

    }

    /**
     * 设置中心点和添加marker 测试数据写死
     *
     * @param map
     * @param isShowLoc
     */
    public void setPosition2CenterTest(BaiduMap map,Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .latitude(29.276107)
                .longitude(106.281771).build();
        map.setMyLocationData(locData);

        if (isShowLoc) {
            LatLng ll = new LatLng(29.276107, 106.281771);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }

    }

    /**
     * 绘制圆，该圆随地图状态变化
     *
     * @return 圆对象
     */
    public void drawCircle() {
        //定义多边形的五个顶点
        LatLng pt1 = new LatLng(29.276107, 106.281771);
//构建用户绘制多边形的Option对象
        /*OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Circle(5, 0xAA00FF00))
                .fillColor(0xAAFFFF00);
//在地图上添加多边形Option，用于显示
        mBaiduMap.addOverlay(polygonOption);*/
        OverlayOptions overlayOptions = new CircleOptions()
                .center(pt1)
                .fillColor(Color.parseColor("#201c3d6f"))
                .stroke(new Stroke(0,Color.parseColor("#ffffff")))
                .radius(300);
        mBaiduMap.addOverlay(overlayOptions);
    }



    //--------------------------------------------------- 地图的生命周期管理 ---------------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        // 退出时销毁定位
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    //--------------------------------------------------- 定位权限的申请 ---------------------------------------------------

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 申请权限成功时
     */
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    void ApplySuccess() {
        initMap();
    }

    /**
     * 申请权限告诉用户原因时
     * @param request
     */
    @OnShowRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
    void showRationaleForMap(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开定位的权限", request);
    }

    /**
     * 申请权限被拒绝时
     *
     */
    @OnPermissionDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    void onMapDenied() {
        Toast.makeText(this,"你拒绝了权限，该功能不可用",Toast.LENGTH_LONG).show();
    }

    /**
     * 申请权限被拒绝并勾选不再提醒时
     */
    @OnNeverAskAgain(Manifest.permission.ACCESS_COARSE_LOCATION)
    void onMapNeverAskAgain() {
        AskForPermission();
    }

    /**
     * 告知用户具体需要权限的原因
     * @param messageResId
     * @param request
     */
    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();//请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    /**
     * 被拒绝并且不再提醒,提示用户去设置界面重新打开权限
     */
    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("当前应用缺少定位权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        if(!mBackKeyPressed){
            showToast("再按一次退出应用");
            mBackKeyPressed = true;
            //延时两秒，如果超出则擦除第一次按键记录
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        }else{
            //环信退出登录
            EMClient.getInstance().logout(true,this);
            //退出程序
            this.finish();
            System.exit(0);
        }
    }

    //--------------------------------------------------- 环信退出登录回调 ---------------------------------------------------
    @Override
    public void onSuccess() {
        Logger.v("环信----------------------------------退出登录成功！");
    }

    @Override
    public void onError(int code, String error) {
        Logger.v("环信----------------------------------退出登录失败：" + error + " 错误码：" + code);
    }

    @Override
    public void onProgress(int progress, String status) {

    }

}