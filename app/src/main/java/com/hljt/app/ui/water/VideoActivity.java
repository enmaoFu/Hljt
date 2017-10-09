package com.hljt.app.ui.water;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hljt.app.R;
import com.hljt.app.base.BaseAty;
import com.hljt.app.base.BaseAty1;
import com.hyphenate.util.Utils;

import butterknife.Bind;

/**
 * @title  视频播放activity
 * @date   2017/09/21
 * @author enmaoFu
 */
public class VideoActivity extends BaseAty1 {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.videoView)
    VideoView videoView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"车载视频");

        //本地的视频  需要在手机SD卡根目录添加一个 chezai.mp4 视频
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.chezai;

        videoView = (VideoView)this.findViewById(R.id.videoView );

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(Uri.parse( uri ));

        //开始播放视频
        videoView.start();

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            showToast("播放完了");
        }
    }

    @Override
    protected void requestData() {

    }
}
