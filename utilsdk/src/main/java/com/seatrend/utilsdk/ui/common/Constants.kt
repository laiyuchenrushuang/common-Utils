package com.seatrend.utilsdk.ui.common

import android.os.Environment

/**
 * Created by seatrend on 2018/8/20.
 *
 * 标志  N -> 新的接口url
 */

class Constants {

    companion object {
        val AES_ENABLE = false //是否开起AES 加密
        val DEBUG: Boolean = true
        val GENGXIN = 66666
        var UPDATA_TIME = " 更新时间: 2020-11-19"
        var GET = "GET"
        var POST = "POST"///vio/getVioByCar
        var IMAGE_FILE = "BicycleImage"//文件夹
        var USE_FILE = "BicycleFile"//文件夹
        var STUDY_FILE = "StudyBicycleFile"//文件夹
        var IMAGE_PATH = Environment.getExternalStorageDirectory().path + "/" + IMAGE_FILE//照片的路径
        var FILE_PATH = Environment.getExternalStorageDirectory().path + "/" + USE_FILE//保存文件的路径

        //        var FILE_PATH = USE_FILE//保存文件的路径
        var STUDY_PATH = Environment.getExternalStorageDirectory().path + "/" + STUDY_FILE//保存文件的路径

        //码表
        var GET_CODE = "/admin/code/getSeaCodeList"  //UD->码表信息（N）
        var GET_ALL_CODE = "/system/code/getSeaCodeAll"  //码表全部拉取接口
        var GET_USER_PERMMISION = "/system/program/getProgramAll" //获取权限
        var USER_PMS = "98" //权限的系统类别
        var PMS_CY = "980101" //查验
        var PMS_DJ = "980102" //登记
        var PMS_FH = "980103" //复核
        var PMS_GD = "980104" //归档
        var PMS_TB = "980105" //退办
        var PMS_YJ = "980201" //预警
        var PMS_TJ = "980202" //统计
        var PMS_BA = "980203" //备案
        var PMS_CX = "980204" //查询
        var PMS_HY = "PMS_HY" //CCC核验
        var PMS_BP = "PMS_BP" //照片补拍


        //登录
        val CZPT = "2"
        var USER_LOGIN = "/pdaUser/pdaLogin"  //账号密码
        var USER_POST_IMAGE = "/config/uploadUserLoginPhoto"  //上传现场照片
        var GLOABAL_PARAMETER = "/config/getSysparam"//
        val TEST: String = "/pdaUser/health"//测试连接
        val PSW_CHANGE: String = "/pdaUser/changePwd"//修改密码
        val ADDSSMM: String = "/pdaUser/addSsmm" //增加手势密码

        // 管理部门
        var GET_GLBM = "/system/department/getDepartmentByGlbm" //获得上级部门
        var GET_GLBMMC = "/system/department/getDepartmentBmmcByGlbm" //根据当前管理部门名称

        //查验
        var CY_ENTRANCE = "/fjdcJscs/getDdcJscu" //查验开始获取信息1
        var CY_ENTRANCE_3C = "/remote/getThreeCertificates" //查验开始获取信息3c
        val SAVE_CY_MSG = "/fjdcJscs/addCheckMessage" //查验保存信息
        val SAVE_CY_PD = "/fjdcJscs/addFjdcHandlerResult" //查验判定信息
        val CAR_SEARCH = "/vehicle/getVehicleByCondition"//车辆查询

        //登记业务
        val YW_ADD_REGISTER_DATA = "/fjdcBusiness/addBikeRegisterData" //业务注册添加

        val YW_GET_ALL_BIKE_DATA = "/fjdcBusiness/getBikeData" //业务登记信息查询
        val YW_GET_YWCZ_BIKE_DATA = "/fjdcBusiness/getBikeMessage" //复核 归档 退办bikeData信息查询

        //复核
        val FH_GET_LIST = "/businessHandling/getFjdcFlowFhList" //复核列表
        val FH_COMMIT = "/businessHandling/recheck" //复核提交

        //退办
        val TB_GET_LIST = "/businessHandling/getFjdcFlowTbList" //退办列表
        val TB_COMMIT = "/businessHandling/updateBusiness" //退办提交

        //原因列表
        val REASON_LIST = "/config/getVerifyNotPassReason" // 复核退办原因


        //归档
        val GD_GET_LIST = "/businessHandling/getFjdcFlowGdList" //归档列表
        val GD_COMMIT = "/businessHandling/updateBusiness" //归档提交

        //视频
        val ADD_VIDEO ="/flowInfo/addFlowInfo"//添加视频
        val GET_VIDEO_LIST = "/flowInfo/getFlowInfoByLsh"//获取视频信息
        val GET_CAMERA_LIST = "/flowInfo/getControlCenterResByTdxh"//获取摄像头
        val GET_VIDEO_URL = "/flowInfo/getVideoUrl"//获取预览URL
        val GET_VIDEO_URL_CALLBACK = "/flowInfo/getVideoBackUrl"//获取回放URL

        val HK_PREVIEW = "/api/video/v2/cameras/previewURLs"  //视频预览
        val HK_PLAYBACK = "/api/video/v2/cameras/playbackURLs" //视频回放


        //图片
//        var PHOTO_INSERT = "/mongoDbFile/mongoDbUploadImageBase64"   //上传图片64
        var PHOTO_INSERT = "/mongoDbFile/upload"   //上传图片
        var PHOTO_MSG_SAVE = "/photo/addPhotoList"   //保存图片
        var PHOTO_MSG_SEARCH = "/file/getPhoto"   //查询图片信息，获取地址
        var PHOTO_SHOW = "/mongoDbFile/download"   //展示图片

        //员工备案
        var YG_SEARCH = "/user/getUserPages"   //员工查询
        var YG_SEARCH_LIST = "/pdaUser/getPdaUserPage"   //员工查询2
        var YG_INSERT = "/pdaUser/addPdaUser"   //员工添加
        var JS_INSERT = "/pdaUser/grantRoleToUser"   //角色添加
        var USER_PSW_UPDATE = "/user/updatePassword"   //修改密码
        var USER_USER_UPDATE = "/pdaUser/updateUser"   //编辑用户
        val FORBIDDEN_COMMIT = "/user/managementUser" //禁用  用户管理 1删除 2停用 3重置密码 4启用
        val YG_JSQX_SEARCH = "/pdaRole/getRoleList" //
        val YG_QUANXIAN = "/pdaUser/getUserByYhdh" //角色类型
        val YG_PSW_RESET = "/pdaUser/resetPwdByYhdh" //重置密码

        val YG_PHOTO_SEARCH = "/user/getUserPhoto" //员工查询图片
        val YG_SEE_GLBM = "/system/department/getDepartmentByGlbm"  //获取管理部门

        //入场业务流水更新
        var UPDATA_LS_ZT = "/entranceInfo/updateEntranceInfo" //入场业务流水更新
        var GET_PERSON = "/personInfo/getPersonInfoPage" // 获取员工信息
        var GET_RC_LSXX = "/entranceInfo/getEntranceInfoByLsh" // 入场流水信息
        var GET_CJ_LSXX = "/photo/getPhotoListByLshAndZplx" // 入场流水信息

        //统计
        var BUSINESS_MESSAGE = "/businessHandling/getFjdcFlowTj" //统计数量

        //号牌号码
        var SYSTEM_PRODUCT_HPHM = "/HphmManager/getHphmUsable"//系统生产号牌号码

        //预警
        var WARNING_MESSAGE = "/config/getBuinessWarningList"//列表
        var WARNING_QS = "/config/signForBuinessWarning" //签收

        //查询车辆信息
        var CAR_MSG_SEARCH = "/fjdcJscs/getBikeInfo" //车辆信息查询

        var GET_CAR_MSG = "/remote/getBikeCccInfo"   //远程接口
        var FACTOTY_GET_CAR_MSG = "/businessHandling/getVehFlowMainsByZcbm"  //业务接口
        var UPDATE_CAR_MSG = "/vehicleTemp/updateVehicleTemp"
        var ADD_CAR_MSG = "/vehicleTemp/addVehicleTemp"
        var SAVE_PHOTO = "/photo/saveFilePhoto"
        var GET_INSURANCEP_MES = "/insurance/getVehInsurance"
        var SAVE_INSURANCEP_MES = "/insurance/saveVehInsurance"
        var GET_COMPANY_NAME = "/insurance/getInsuranceCompanys"
        var DECRYPT_INSURANCE_MSG = "/insurance/decryptVehInsurance"
        var GET_INSURANCE_DETAILS = "/insurance/finishInsurance"
        var DOWNLOAD_PHOTO = "/photo/downloadPhoto"
        var VERIFY = "/validation/validationStatusOfBuyCar"
        var EXIT_LOGIN = "/user/logout" //退出 (N)
        var GET_QR_CODE_BY_LSH = "/vehicleTemp/getQrCodeByLshAndXh"
        var GET_PHOTO_TYPE = "/Configuration/getConfigList"
        var SAVE_CAR_CY_URL = "/vehicleTemp/addVehicleAndFlow"
        var GET_CAR_LLZM_BY_HDFS = "/Configuration/geParticulartConfigList"
        var PSOT_CAR_YWXX = "/businessHandling/updateOwnerMessage" //业务类接口
        var POST_HPBF = "/businessHandling/licensePlate"
        var POST_ONE_PICTURE = "/photo/savePhoto" //file流耽搁传照片业务
        var GET_LSH_MSG = "/businessHandling/getVehFlowMainByLsh"
        var GET_LLZM_MB = "/Configuration/getConfigList"
        var POST_DAGD = "/businessHandling/filing"
        var PDF_GET_CY = "/file/getbikeCheckPdfBylsh"//PDF 查验
        var PDF_GET_DJ = "/file/getbikeRegisterPdfBylsh"//PDF 登记
        var URL_ZPBP = "/businessHandling/getRecoverPhoto"//PDF 登记

        //入场
        var ADD_CAR_RC = "/entranceInfo/addEntranceInfo"//车辆入场信息
        var ADD_CAR_XX = "/vehicle/addVehicle" //手动添加车辆信息
        var ADD_CAR_KRY_RC = "/checkResult/addCheckResult"//关键部位检查
        var ADD_CAR_KRY_LIST = "/checkResult/getItemByRllx"//关键部位列表

        //相关的key值
        var HTTP = "http://"
        var HTTPS = "https://"

        //拆解
        var GET_LIST_CJ = "/flowInfo/getFlowInfoPage"
        val CJ_GW: String = "/siteInfo/getChannelListByCdmc"
        val CJ_WC: String = "/flowInfo/updateFlowInfoZt"//拆解完成


        var IP_DEFAULT = "11.121.35.182"
        var PORT_DEFAULT = "9011"

        var NET_KEY = "/scrapCarSystem"  //监销的特点  http://11.121.35.182:9011/scrapCarSystem
        var DEFAULT_NET_URL = (HTTP + IP_DEFAULT + ":" + PORT_DEFAULT + NET_KEY)
        var SETTING = "setting"
        var IP_K = "ip"
        var IP_HK_K = "hk_ip"
        var PORT_K = "port"
        var PORT_HK_K = "hk_port"
        var NET_K = "network"
        var NAME = "name"
        var NUMBER = "number"
        var ADDRESS = "address"
        var ADMAIN = "admain"
        var PHOTO = "photo"
        var XTLB_00 = "00"
        var XTLB_01 = "01"
        var SFZMMC = "2019" //身份证明名称系统类别
        var CLLX = "1007" //车辆类型系统类别
        var SYXZ = "1003"   //使用性质
        var CSYS = "1008"   //车身颜色
        var HDFS = "0001"   //获得方式
        var YWLX = "0061"   //业务类型
        var LLZM = "0010"   //来历证明（自定义的没意义）
        var CLYT = "1006"  //车辆用途
        var XSQY = "0033"  //所在区域 ->地区  注意 行驶区域是写死的
        val CHANGE_USER: String? = "1" //用户编辑界面flag

        var CYZP = "1010" // 查验照片类型
        var DJZP = "1011" // 登记照片类型
        var YGZP = "1012" // 员工照片类型
        var DMLB_HPZL = "1007" // 号牌种类类型
        var DMLB_CLLX = "1006" // 车辆类型类型
        var DMLB_CLLX1 = "1004" // 车辆类型类型
        var DMLB_RLZL = "0009" // 燃料类型01

        //照片请求

        var GET_PHOTOS = "/photoSetting/getPhotoList"

        //行政区划
        var QH_CONFIG = "1" // 1是为新疆，2是为四川  默认的城市显示
        var QH_SHENG = "/system/code/getSeaCodeProvince" //获取省列表
        var QH_SHI = "/system/code/getSeaCodeLowerLevel" //获取市列表  （新疆 xzqh 650000 市级type 0  成都xzqh 510000）
        var MY_QH_SHENG_DMLB = "0219"  //
        var SI_CHUAN = "510000"
        var XIN_JIANG = "650000"

        // 业务原因
        var BG_YY = "1021"  //变更
        var ZY_YY = "1020"  //转移
        var ZR_YY = "1022"  //转入

        //判定项目
        var PD_LIST = "1023"  //判定项目

        //":"
        val MAO_HAO = ":"

        var SYN_CODE = "syncode"
        var IS_FIRST = "is_first"
        var QAUTH = "Authorization"
        var PATH = "path"
        var ZPLX = "zplx"  //这个是照片名称
        var ZPMC = "zpmc" // 这个是代码值
        var TYPE = "type"
        var TYPE_ZCBM = "XXXXXXX"  //整车编码类型 zplx
        var TYPE_TYH = "XXXXXXX"  //拓印号 zplx
        var TYPE_QT = "999"  //其他 zplx
        var REQUEST_ZCBM = 219 // 请求request id
        var CLIPP = "clipped" // 是否裁剪   最好写死在拍照采集界面 控制 已裁剪名称
        var CLIPP_PICTURE_PATH = "clipped_path" // 是否裁剪

        var TJQSSJ = "tjqssj"
        var TJZZSJ = "tjzzsj"

        var POSITION = "position"
        var TO_VIN = "tovin"
        var LSH = "lsh"
        var XH = "xh"

        var TASK = "task"
        var LX = "getInfoLX"  // 接口类型 1  外网(业务类型只有注册登记)， 2  业务内网（均有）
        var VEISION_TIME = "vtime" //同步代码的版本时间
        var VEISION = "veision" //同步代码的版本

        // 0是VIN，1是查验，2注册，3变更 ，4转移， 5补换，6注销，7旧车换牌， 8临时号牌申请 9车辆归档

        val CAR_YW: String = "A" //车辆业务

        val CAR_CY: String = "1" //车辆查验
        val CAR_ZC: String = "2" //车辆注册
        val CAR_BG: String = "3" //车辆变更
        val CAR_ZY: String = "4" //车辆转移
        val CAR_BH: String = "5" //车辆补换
        val CAR_ZX: String = "6" //车辆注销

        //筛选列表
        val QB = "全部"
        val DCJ = "待拆解"
        val CJZ = "拆解中"
        val DFH = "待复核"
        val FHTG = "复核通过"
        val FHBTG = "复核不通过"

        val CAR_JCHP: String = "7" //旧车换牌
        val CAR_LSHP: String = "8" //临时号牌
        val CAR_ZR: String = "9" //车辆转入
        val YGBA: String = "10" //员工备案


        val YWTB: String = "11" //业务退办
        val YWBP: String = "12" //照片补拍


        val CAR_RC = "13"//车辆入场
        val CAR_CJQ = "14"//拆解前
        val CAR_CJH = "15"//拆解后

        val SFZ_SYR: Int = 0x101 //所有人身份证
        val SFZ_DLR: Int = 0x102 //代理人身份证
        val SFZ_YJ: Int = 0x103  //邮寄身份证
        val FACE: Int = 0x104  //人脸识别
        val ID_CARD_READ_CODE: Int = 0x105  //NFC

        val UI_TYPE: String = "UI_TYPE" // 0是业务复核，1是档案归档,2是业务退办 3 车辆查询
        val FORBIDDEN: String = "type_forbidden"  // 1 禁用显示，0 正常显示

        //service Phtoto

        val S_LSH: String? = "lsh" //流水号
        val S_XH: String? = "xh" //序号
        val S_ZPZL: String? = "zpzl" //照片种类
        val S_ZPDZ: String? = "zpdz" // 照片id
        val S_ZPSM: String? = "zpsm" // 车辆识别代号
        val S_CFFS: String? = "cffs" // 存放方式
        val S_LRR: String? = "lrr" // 人
        val S_LRBM: String? = "lrbm" // 部门
        val S_ZPPATH: String? = "zpPath" //照片加载路径
        val S_SFZ: String? = "sfzmhm" //身份证
        val S_ZPLX: String? = "zplx" //照片类型(1查验照片，2登记照片)
        val S_HPHM: String? = "hphm" //号牌号码
        val S_CLLX: String? = "cllx" //车辆类型
        val S_SCCS: String? = "zpcs" //照片上传次数
        val S_ZPBASE64: String? = "zpbase64" //照片base64
        var PTOTO_UPLOAD_ACTION = "photo_upload_action"
        var DATA = "data"

        //员工
        val RXM = "rxm"
        val RXM_MC = "身份证正面(人像面)"
        val GHM = "ghm"
        val GHM_MC = "身份证正面(国徽面)"
        val XCZP = "xczp"
        val XCZP_MC = "现场照片"
        val QMZ = "00"
        val QMZ_MC = "签名照片"

        //业务信息用到的码表

        val ZY_BA = "BA"  //辖区内转移不换号
        val ZY_BB = "BB"  //辖区内转移换号
        val ZY_BC = "BC"  //辖区外转移
        val BG_DA = "DA" //变更迁出
        val BG_DB = "DB" //变更所有人
        val BG_DC = "DC" //变更车身颜色
        val ZR_IA = "IA" //转入辖区
        val ZR_IB = "IB" //转入变更

        val A = "A" //注册登记
        val B = "B" //转移登记
        val D = "D" //变更登记
        val I = "I" //转入登记
        val G = "G" //注销登记
        val K = "K" //补换登记
        val J = "J" //旧车换牌登记

        //判定车身颜色
        val PD_CSYS = "C"
        val BA_GLY = "B00" //备案 管理员屏蔽
    }
}
