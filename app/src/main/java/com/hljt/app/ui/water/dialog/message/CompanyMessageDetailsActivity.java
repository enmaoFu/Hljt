package com.hljt.app.ui.water.dialog.message;

import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hljt.app.R;
import com.hljt.app.base.BaseAty;

import butterknife.Bind;

/**
 * @title  应用辅助软件-》事故单位信息详情activity
 * @date   2017/09/30
 * @author enmaoFu
 */
public class CompanyMessageDetailsActivity extends BaseAty{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    /*@Bind(R.id.img)
    SimpleDraweeView img;*/
    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_message_details;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"实景图");
        /*Uri uri = Uri.parse("http://www.dingjian.com/uploads/allimg/130204/1-130204120R00-L.png");
        img.setImageURI(uri);*/
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //WebView加载web资源
        webview.loadUrl("http://www.bacaiyu.com/me/jj/");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void requestData() {

    }
}
