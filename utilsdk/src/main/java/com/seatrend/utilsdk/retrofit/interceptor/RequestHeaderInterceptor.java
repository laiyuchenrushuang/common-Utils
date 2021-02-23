package com.seatrend.utilsdk.retrofit.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by ly on 2020/7/1 18:09
 */
public class RequestHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Content-Type", "text/xml;charset=UTF-8");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
