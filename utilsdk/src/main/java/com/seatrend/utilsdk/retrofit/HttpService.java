package com.seatrend.utilsdk.retrofit;

import android.util.Log;
import androidx.annotation.NonNull;

import com.seatrend.utilsdk.retrofit.base.BaseEntity;
import com.seatrend.utilsdk.retrofit.base.ConmmonResponse;
import com.seatrend.utilsdk.retrofit.inter.IRoutingService;
import com.seatrend.utilsdk.retrofit.inter.base.BaseService;
import com.seatrend.utilsdk.retrofit.interceptor.TokenInterceptor;
import com.seatrend.utilsdk.utils.GsonUtils;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ly on 2020/6/30 16:06
 */
public class HttpService {

    private static HttpService httpservice = null;
    private static Retrofit retrofit = null;

    private static final String MY_TAG = "Http[lylog]";
    private static final Long CONNECT_TIME_OUT = 20L;
    private static final Long WRITE_TIME_OUT = 20L;
    private static final Long READ_TIME_OUT = 20L;

    //////http://11.1.1.73:8086/ProvinceService2/trffweb/services/TmriOutNewAccess?wsdl
    private static String BASE_URL = "";
    private static String TOKEN_VALUE = "";

    private static Interceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            try {
                message = message.replaceAll("%(?![0-9a-fA-F]{2})","%25");
                String text = URLDecoder.decode(message, "utf-8");
                Log.i(MY_TAG, text);
            } catch (UnsupportedEncodingException e) {
                Log.i(MY_TAG, e.getMessage());
            }
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);


    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .build();

    private static OkHttpClient okHttpClientTokened = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .addInterceptor(new TokenInterceptor().setTokenValue(TOKEN_VALUE))
            .build();

    public static HttpService getInstance() {
        if (httpservice == null) {
            synchronized (HttpService.class) {
                if (httpservice == null) {
                    httpservice = new HttpService();
                }
            }
        }
        return httpservice;
    }

    private synchronized Retrofit getRetrofit(String showUrl, boolean tolkened) {

        if (tolkened) {
            return new Retrofit.Builder()
                    .baseUrl(showUrl)
                    .client(okHttpClientTokened)
                    .addConverterFactory(GsonConverterFactory.create())  //转换器
                    .build();
        }
        return new Retrofit.Builder()
                .baseUrl(showUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())  //转换器
                .build();
    }

    public <T> T getApiService(String url, Class<T> service, boolean tolkened) {
        return getRetrofit(url, tolkened).create(service);
    }

    public HttpService setBaseUrl(String url) {
        BASE_URL = url;
        return this;
    }

    public HttpService setTokenValue(String token) {
        TOKEN_VALUE = token;
        return this;
    }

    //==========================================================POST<Start>===========================================================================
    //post map
    public <T> void posT(final String baseurl, final String url, Map<String, String> map, Class<T> service, boolean tolkened, final NormalView normalView) {
        BASE_URL = baseurl;
        BaseService innerService = (BaseService) getApiService(baseurl, service, tolkened);
        Call<ResponseBody> call = innerService.postServer(url, map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                if(!"security_check".equals(url)){ //登录接口 返回不是标准格式
                    if (response.isSuccessful()) {
                        try {
                            String resp = responseBody.string();
                            BaseEntity entity = GsonUtils.gson(resp,BaseEntity.class);
                            if(entity!=null && 0 == entity.getCode()){  //0 是服务器正式返回
                                result.responseString = resp;
                                normalView.netWorkTaskSuccess(result);
                            }else {
                                result.responseString = entity.getMessage();
                                normalView.netWorkTaskfailed(result);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            result.responseString = e.getMessage();
                            normalView.netWorkTaskfailed(result);
                        }
                    } else {
                        try {
                            result.responseString = (response.errorBody() != null ? response.errorBody().string() : null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        normalView.netWorkTaskfailed(result);
                    }
                }else {
                    try {
                        result.responseString = responseBody != null ? responseBody.string() : null;
                        normalView.netWorkTaskSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.responseString = e.getMessage();
                        normalView.netWorkTaskfailed(result);
                    }
                }


            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                result.responseString = t.getMessage();
                normalView.netWorkTaskfailed(result);
            }
        });

    }


    //post json
    public <T> void postJson(final String baseurl, final String url, String json, Class<T> service, boolean tolkened, final NormalView normalView) {
        BASE_URL = baseurl;

        BaseService innerService = (BaseService) getApiService(baseurl, service, tolkened);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        Call<ResponseBody> call = innerService.postServer(url, requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                ConmmonResponse result = new ConmmonResponse();
                result.url = url;

                if (response.isSuccessful()) {
                    try {
                        String resp = responseBody.string();
                        BaseEntity entity = GsonUtils.gson(resp,BaseEntity.class);
                        if(entity!=null && 0 == entity.getCode()){  //0 是服务器正式返回
                            result.responseString = resp;
                            normalView.netWorkTaskSuccess(result);
                        }else {
                            result.responseString = entity.getMessage();
                            normalView.netWorkTaskfailed(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.responseString = e.getMessage();
                        normalView.netWorkTaskfailed(result);
                    }
                } else {
                    try {
                        result.responseString = (response.errorBody() != null ? response.errorBody().string() : null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    normalView.netWorkTaskfailed(result);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                result.responseString = t.getMessage();
                normalView.netWorkTaskfailed(result);
            }
        });

    }


    //==========================================================POST<End>===========================================================================

    //==========================================================GET<Start>===========================================================================

    //get by map
    public <T> void geT(final String baseurl, final String url, Map<String, String> map, Class<T> service, boolean tolkened, final NormalView normalView) {
        BASE_URL = baseurl;
        BaseService innerService = (BaseService) getApiService(baseurl, service, tolkened);
        Call<ResponseBody> call = innerService.getServer(url, map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                ConmmonResponse result = new ConmmonResponse();
                result.url = url;

                if (response.isSuccessful()) {
                    try {
                        String resp = responseBody.string();
                        BaseEntity entity = GsonUtils.gson(resp,BaseEntity.class);
                        if(entity!=null && 0 == entity.getCode()){  //0 是服务器正式返回
                            result.responseString = resp;
                            normalView.netWorkTaskSuccess(result);
                        }else {
                            result.responseString = entity.getMessage();
                            normalView.netWorkTaskfailed(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.responseString = e.getMessage();
                        normalView.netWorkTaskfailed(result);
                    }
                } else {
                    try {
                        result.responseString = (response.errorBody() != null ? response.errorBody().string() : null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    normalView.netWorkTaskfailed(result);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                result.responseString = t.getMessage();
                normalView.netWorkTaskfailed(result);
            }
        });
    }

    //==========================================================GET<End>===========================================================================


    //==========================================================文件下载<Start>===========================================================================
    public void download(String url, final String path, final DownloadListener downloadListener) {
        retrofit = new Retrofit.Builder()
                //test url： https://www.baidu.com/img/bd_logo1.png
//            .baseUrl(BASE_URL)
                .baseUrl(url)
                .client(okHttpClient)
                //通过线程池获取一个线程，指定callback在子线程中运行。
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        IRoutingService requestService = retrofit.create(IRoutingService.class);
        Call<ResponseBody> call = requestService.download();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                //将Response写入到从磁盘中，详见下面分析
                //注意，这个方法是运行在子线程中的
                writeResponseToDisk(path, response, downloadListener);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                downloadListener.onFail("网络错误～");
            }
        });
    }

    private static void writeResponseToDisk(String path, Response<ResponseBody> response, DownloadListener downloadListener) {
        //从response获取输入流以及总大小
        writeFileFromIS(new File(path), response.body().byteStream(), response.body().contentLength(), downloadListener);
    }

    private static int sBufferSize = 4 * 1024;

    //将输入流写入文件
    private static void writeFileFromIS(File file, InputStream is, long totalLength, DownloadListener downloadListener) {
        //开始下载
        downloadListener.onStart();

        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFail("createNewFile IOException");
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] data = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("IOException");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//==========================================================文件下载<End>===========================================================================


//==========================================================文件上传<start>===========================================================================

    //post files
    public <T> void postMapFiles(final String baseurl, final String url, List<String> fileList, Class<T> service, boolean tolkened, final NormalView normalView) {
        BASE_URL = baseurl;
        BaseService innerService = (BaseService) getApiService(baseurl, service, tolkened);

        Map<String, RequestBody> params = new HashMap<>();
        for (String db : fileList) {
            params.put(db, RequestBody.create(MediaType.parse("multipart/form-data"), new File(db)));
        }

        Call<ResponseBody> call = innerService.uploadMapFile(url, params);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                ConmmonResponse result = new ConmmonResponse();
                result.url = url;

                if (response.isSuccessful()) {
                    try {
                        String resp = responseBody.string();
                        BaseEntity entity = GsonUtils.gson(resp,BaseEntity.class);
                        if(entity!=null && 0 == entity.getCode()){  //0 是服务器正式返回
                            result.responseString = resp;
                            normalView.netWorkTaskSuccess(result);
                        }else {
                            result.responseString = entity.getMessage();
                            normalView.netWorkTaskfailed(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.responseString = e.getMessage();
                        normalView.netWorkTaskfailed(result);
                    }
                } else {
                    try {
                        result.responseString = (response.errorBody() != null ? response.errorBody().string() : null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    normalView.netWorkTaskfailed(result);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                result.responseString = t.getMessage();
                normalView.netWorkTaskfailed(result);
            }
        });

    }

    //post one file
    public <T> void postOneFile(final String baseurl, final String url, File file, Class<T> service, boolean tolkened, final NormalView normalView) {
        BASE_URL = baseurl;
        BaseService innerService = (BaseService) getApiService(baseurl, service, tolkened);

        Map<String, RequestBody> params = new HashMap<>();

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                RequestBody.create(file, MediaType.parse("image/jpg"));
//                RequestBody.create(file, MediaType.parse("image/jpeg"));
//                RequestBody.create(file, MediaType.parse("application/octet-stream"));

        //注意这个file名字要和服务器一致
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(),requestFile);

        Call<ResponseBody> call = innerService.uploadOneFile(url, part);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                ConmmonResponse result = new ConmmonResponse();
                result.url = url;

                if (response.isSuccessful()) {
                    try {
                        String resp = responseBody.string();
                        BaseEntity entity = GsonUtils.gson(resp,BaseEntity.class);
                        if(entity!=null && 0 == entity.getCode()){  //0 是服务器正式返回
                            result.responseString = resp;
                            normalView.netWorkTaskSuccess(result);
                        }else {
                            result.responseString = entity.getMessage();
                            normalView.netWorkTaskfailed(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.responseString = e.getMessage();
                        normalView.netWorkTaskfailed(result);
                    }
                } else {
                    try {
                        result.responseString = (response.errorBody() != null ? response.errorBody().string() : null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    normalView.netWorkTaskfailed(result);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                result.responseString = t.getMessage();
                normalView.netWorkTaskfailed(result);
            }
        });

    }

    //post one file +参数
    public <T> void postOneFileAndMap(final String baseurl, final String url, File file, Map<String, String> map,Class<T> service, boolean tolkened, final NormalView normalView) {
        BASE_URL = baseurl;
        BaseService innerService = (BaseService) getApiService(baseurl, service, tolkened);

        Map<String, RequestBody> params = new HashMap<>();

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                RequestBody.create(file, MediaType.parse("image/jpg"));
//                RequestBody.create(file, MediaType.parse("image/jpeg"));
//                RequestBody.create(file, MediaType.parse("application/octet-stream"));

        //注意这个file名字要和服务器一致
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(),requestFile);

        Call<ResponseBody> call = innerService.uploadOneFileAndMap(url, part,map);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                ConmmonResponse result = new ConmmonResponse();
                result.url = url;

                if (response.isSuccessful()) {
                    try {
                        String resp = responseBody.string();
                        BaseEntity entity = GsonUtils.gson(resp,BaseEntity.class);
                        if(entity!=null && 0 == entity.getCode()){  //0 是服务器正式返回
                            result.responseString = resp;
                            normalView.netWorkTaskSuccess(result);
                        }else {
                            result.responseString = entity.getMessage();
                            normalView.netWorkTaskfailed(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.responseString = e.getMessage();
                        normalView.netWorkTaskfailed(result);
                    }
                } else {
                    try {
                        result.responseString = (response.errorBody() != null ? response.errorBody().string() : null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    normalView.netWorkTaskfailed(result);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ConmmonResponse result = new ConmmonResponse();
                result.url = url;
                result.responseString = t.getMessage();
                normalView.netWorkTaskfailed(result);
            }
        });

    }


//==========================================================文件上传<End>===========================================================================
}
