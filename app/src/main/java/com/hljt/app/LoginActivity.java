package com.hljt.app;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hljt.app.base.BaseAty;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @title  登录
 * @date   2017/09/26
 * @author enmaoFu
 */
public class LoginActivity extends BaseAty{

    @Bind(R.id.input_user)
    EditText inputUser;
    @Bind(R.id.input_pwd)
    EditText inputPwd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,50);
    }

    @OnClick({R.id.login_but})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.login_but:
                String userName = inputUser.getText().toString().trim();
                String userPwd = inputPwd.getText().toString().trim();
                if(userName.length() == 0){
                    showErrorToast("用户名不能为空");
                }else if(userPwd.length() == 0){
                    showErrorToast("密码不能为空");
                }else{
                    showToast("登录成功");
                    setHasAnimiation(false);
                    /*if (UserManger.isLogin()) {
                        startActivity(MainAty.class, null);
                    } else {
                        startActivity(LoginAty.class, null);
                    }*/
                    startActivity(MainActivity.class, null);
                    overridePendingTransition(R.anim.aty_in, R.anim.activity_alpha_out);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
                break;
        }
    }

    @Override
    protected void requestData() {

    }

}
