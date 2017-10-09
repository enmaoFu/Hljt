package com.em.baseframe.application;

import android.app.Application;
import android.content.Context;

import com.em.baseframe.util.RetrofitUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.litepal.LitePalApplication;

import static com.orhanobut.logger.LogLevel.FULL;

/**
 * @title  基础的全局类
 * @date   2017/06/17
 * @author enmaoFu
 */
public class BaseApplication extends LitePalApplication {

    public static Context mContext;

    private static String TAG = "print";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RetrofitUtils.init(null, null);
        initLogger();
        initFresco();
    }

    public void initLogger(){
        Logger.init(TAG)
                //打印所有的日志,NONE不打印日志
                .setLogLevel(LogLevel.FULL);
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    public static Context getApplicationCotext() {
        return mContext.getApplicationContext();
    }

}
