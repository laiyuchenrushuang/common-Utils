package com.seatrend.utilsdk.retrofit.inter.base;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.net.URL;
import java.util.Map;

/**
 * Created by ly on 2020/6/30 17:40
 */
public interface BaseService {
    @FormUrlEncoded
    @POST()
    Call<ResponseBody> postServer( @Url String url, @FieldMap Map<String, String> map) ;

    @POST()
    Call<ResponseBody> postServer( @Url String url, @Body RequestBody requestBody) ;

    @GET()
    Call<ResponseBody> getServer(@Url String url,@QueryMap  Map<String, String> map);

    //多个文件
    @Multipart
    @POST()
    Call<ResponseBody> uploadMapFile( @Url String url,@PartMap  Map<String, RequestBody> params);

    //单个文件
    @Multipart
    @POST()
    Call<ResponseBody> uploadOneFile( @Url String url, @Part  MultipartBody.Part params);

    //单个文件 包含参数
    @Multipart
    @POST()
    Call<ResponseBody> uploadOneFileAndMap( @Url String url, @Part  MultipartBody.Part params,@PartMap  Map<String, String> map);
}
