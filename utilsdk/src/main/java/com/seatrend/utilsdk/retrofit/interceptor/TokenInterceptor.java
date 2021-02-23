package com.seatrend.utilsdk.retrofit.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by ly on 2020/7/1 18:17
 */
public class TokenInterceptor implements Interceptor {

    private String TOKEN_KEY = "Authorization";
    private String TOKEN_VALUE = "";

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header(TOKEN_KEY, TOKEN_VALUE);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    public TokenInterceptor setTokenValue(String value) {
        this.TOKEN_VALUE = value;
        return this;
    }

    public TokenInterceptor setTokenKey(String key) {
        this.TOKEN_KEY = key;
        return this;
    }
}
