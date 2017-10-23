package com.hljt.app;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.em.baseframe.util.RetrofitUtils;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hljt.app.base.BaseAty;
import com.hljt.app.http.HttpInterface;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

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
                    showLoadingDialog(null);
                    doHttp(RetrofitUtils.createApi(HttpInterface.class).toLogin(userName,userPwd),1);
                }
                break;
        }
    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onSuccess(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onSuccess(result, call, response, what);
        switch (what){
            case 1:
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
                break;
        }
    }

    @Override
    public void onFailure(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onFailure(result, call, response, what);
        switch (what){
            case 1:
                showErrorToast("用户名或密码错误");
                break;
        }
    }
}
