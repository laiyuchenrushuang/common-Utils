package com.seatrend.utilsdk.httpserver.common;

public interface Constants {

    //不需要token的结果集
    String[] NO_TOKEN = {""};

    interface Companion {

        String GET = "GET";
        String POST = "POST";
        String DELETE = "DELETE";
        String PATCH = "PATCH";
        String AUTH = "Authorization";

        Boolean AES_ENABLE = false; //AES开关

    }


}
