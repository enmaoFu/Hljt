package com.hljt.app.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @POST("ods-backstage-web/tologin")
    @FormUrlEncoded
    Call<ResponseBody> toLogin(@Field("user") String user, @Field("pwd") String pwd);

    @GET("ods_manager_web/alarm/init")
    Call<ResponseBody> init();

    @POST("ods-resources-web/water/area")
    @FormUrlEncoded
    Call<ResponseBody> area(@Field("lat") String lat, @Field("lon") String lon, @Field("raidus") String raidus);

    @GET("ods-resources-web/water/single")
    Call<ResponseBody> single(@Query("fireWaterId") String fireWaterId, @Query("fireWaterTypeId") String fireWaterTypeId);

    @GET("ods-resources-web/linkage/all")
    Call<ResponseBody> all();

    @POST("ods_manager_web/monitor/road")
    @FormUrlEncoded
    Call<ResponseBody> road(@Field("val") String val);

}
