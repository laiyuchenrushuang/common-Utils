package com.seatrend.utilsdk.retrofit

import com.seatrend.utilsdk.retrofit.base.ConfigInfo
import com.seatrend.utilsdk.utils.Constants
import java.io.File

/**
 * Created by ly on 2020/6/30 18:18
 */
object HttpRequest {

    fun <T> posT(url: String, map: Map<String, String>, service: Class<T>, tolkened: Boolean, normalView: NormalView) {
        if (tolkened) {
            HttpService.getInstance().setTokenValue(ConfigInfo.TOKEN)
                .posT(Constants.BASE_URL, url, map, service, tolkened, normalView)
            return
        }
        HttpService.getInstance().posT(Constants.BASE_URL, url, map, service, tolkened, normalView)
    }

    fun <T> geT(url: String, map: Map<String, String>, service: Class<T>, tolkened: Boolean, normalView: NormalView) {
        if (tolkened) {
            HttpService.getInstance().setTokenValue(ConfigInfo.TOKEN)
                .geT(Constants.BASE_URL, url, map, service, tolkened, normalView)
            return
        }
        HttpService.getInstance().geT(Constants.BASE_URL, url, map, service, tolkened, normalView)
    }

    fun <T> postJson(url: String, json: String, service: Class<T>, tolkened: Boolean, normalView: NormalView) {
        if (tolkened) {
            HttpService.getInstance().setTokenValue(ConfigInfo.TOKEN)
                .postJson(Constants.BASE_URL, url, json, service, tolkened, normalView)
            return
        }
        HttpService.getInstance().postJson(Constants.BASE_URL, url, json, service, tolkened, normalView)
    }

    fun <T> postMapFiles(url: String, fileList: List<String>, service: Class<T>, tolkened: Boolean, normalView: NormalView) {
        if (tolkened) {
            HttpService.getInstance().setTokenValue(ConfigInfo.TOKEN)
                .postMapFiles(Constants.BASE_URL, url, fileList, service, tolkened, normalView)
            return
        }
        HttpService.getInstance().postMapFiles(Constants.BASE_URL, url, fileList, service, tolkened, normalView)
    }

    fun <T> postOneFile(url: String, file: File, service: Class<T>, tolkened: Boolean, normalView: NormalView) {
        if (tolkened) {
            HttpService.getInstance().setTokenValue(ConfigInfo.TOKEN)
                .postOneFile(Constants.BASE_URL, url, file, service, tolkened, normalView)
            return
        }
        HttpService.getInstance().postOneFile(Constants.BASE_URL, url, file, service, tolkened, normalView)
    }

    fun <T> postOneFileAndMap(url: String, file: File, map: Map<String, String>,service: Class<T>, tolkened: Boolean, normalView: NormalView) {
        if (tolkened) {
            HttpService.getInstance().setTokenValue(ConfigInfo.TOKEN)
                .postOneFileAndMap(Constants.BASE_URL, url, file,map, service, tolkened, normalView)
            return
        }
        HttpService.getInstance().postOneFileAndMap(Constants.BASE_URL, url, file, map,service, tolkened, normalView)
    }
}
