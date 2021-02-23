package com.seatrend.utilsdk.retrofit.base;

/**
 * Created by ly on 2020/6/30 17:23
 */
public class ConmmonResponse {
    public String url; //接口
    public String responseString; //响应信息

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
