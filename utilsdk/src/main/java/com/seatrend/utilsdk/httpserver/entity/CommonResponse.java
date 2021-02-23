package com.seatrend.utilsdk.httpserver.entity;

/**
 * Created by seatrend on 2018/8/27.
 */

public class CommonResponse {

    public String url; //接口
    public String responseString; //响应信息
    public String method;//请求方式

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }
}
