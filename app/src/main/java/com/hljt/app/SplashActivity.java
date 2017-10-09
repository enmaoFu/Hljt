package com.hljt.app;

import android.os.Handler;
import com.hljt.app.base.BaseAty;

/**
 * @title  欢迎页
 * @date   2017/09/26
 * @author enmaoFu
 */
public class SplashActivity extends BaseAty{

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        Handler mHandle = new Handler();
        mHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                goActivity();
            }
        }, 1000);
    }

    private void goActivity() {
        setHasAnimiation(false);
        /*if (UserManger.isLogin()) {
            startActivity(MainAty.class, null);
        } else {
            startActivity(LoginAty.class, null);
        }*/
        startActivity(LoginActivity.class, null);
        overridePendingTransition(R.anim.aty_in, R.anim.activity_alpha_out);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    @Override
    public void requestData() {

    }
}
