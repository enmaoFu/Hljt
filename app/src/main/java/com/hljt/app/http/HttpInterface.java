package com.hljt.app.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/25.
 */

public interface HttpInterface {

    /**
     * 格式
     *
     * @return
     */
    /*@POST("nw/staMessage")
    @FormUrlEncoded
    Call<ResponseBody> staMessage(@Field("pageSize") String pageSize, @Field("targetPage") String targetPage);*/

    /**
     * 上传定位点
     */
    @POST("navigation-1.0-SNAPSHOT/navigation")
    @FormUrlEncoded
    Call<ResponseBody> navigation(@Field("latitude") double latitude, @Field("longitude") double longitude, @Field("loc_time") String loc_time);

}
