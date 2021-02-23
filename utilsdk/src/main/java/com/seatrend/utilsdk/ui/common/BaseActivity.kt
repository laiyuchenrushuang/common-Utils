package com.seatrend.utilsdk.ui.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import com.seatrend.utilsdk.R
import com.seatrend.utilsdk.entity.MessageEntity
import com.seatrend.utilsdk.httpserver.entity.CommonProgress
import com.seatrend.utilsdk.httpserver.entity.CommonResponse
import com.seatrend.utilsdk.manager.AppManager
import com.seatrend.utilsdk.utils.*
import java.io.File
import java.util.*
import java.util.regex.Pattern

/**
 * Created by seatrend on 2018/8/20.
 */

@Suppress("DEPRECATED_IDENTITY_EQUALS")
abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected var mDialogListenr: DialogListener? = null
    private val CAMERA_REQUEST_CODE = 12
    protected val IDCARDIMAGEPATH =
        Environment.getExternalStorageDirectory().path + "/" + Constants.IMAGE_PATH + "/photo/scanManger"
    protected val FACEPATH =
        Environment.getExternalStorageDirectory().path + "/" + Constants.IMAGE_PATH + "/photo/facePath"  //人脸

    protected val headSfzPath =
        Environment.getExternalStorageDirectory().path + "/" + Constants.IMAGE_PATH + "/photo/ocr.jpg"
    protected var imgOCRFile: File? = null   // OCR的图片
    protected var imgFaceFile: File? = null  //人脸识别的图片
    var ivBack: ImageView? = null
    var ivRight: ImageView? = null
    var tvPageTitle: TextView? = null
    var tvRight: TextView? = null
    var rlParent: RelativeLayout? = null
    private var noDataView: View? = null

    //    val ID_CARD_READ_CODE = 10   //为什么没用了，因为很多个身份证采集，这个只是一个id，没法去人所有人代理人邮寄人
    val LIMIT_TIME: Int = 30 * 60 * 1000
    var currentTime: Long = SystemClock.uptimeMillis()

    private var mActivityJumpTag: String? = null       //activity跳转tag
    private var mClickTime: Long? = 0L  //事件间隔time
    private var LIMIT_CLICK_TIME: Long = 1000 //限制跳转界面的时间

    //TextView 和 ScollView 冲突监听器
    val onTouchListener = View.OnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            //父节点不拦截子节点
            v.parent.requestDisallowInterceptTouchEvent(true)
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            //父节点不拦截子节点
            v.parent.requestDisallowInterceptTouchEvent(true)
        } else if (event.action == MotionEvent.ACTION_UP) {
            //父节点拦截子节点
            v.parent.requestDisallowInterceptTouchEvent(false)
        }
        false
    }

    //输入进行过滤 只能输入汉字，字母，英文

    val inputFilter = object : InputFilter {

//        var pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]")
//        override fun filter(charSequence: CharSequence, i: Int, i1: Int, spanned: Spanned, i2: Int, i3: Int): CharSequence? {
//            val matcher = pattern.matcher(charSequence)
//            if (!matcher.find()) {
//                return null
//            } else {
//                showToast("只能输入汉字,英文，数字")
//                return ""
//            }
//        }

        //保留“-“ 方便门牌输入 "." "·"
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            for (i in start until end) {
                if (!Character.isLetterOrDigit(source[i])
                    && Character.toString(source[i]) != "_"
                    && Character.toString(source[i]) != "-"
                    && Character.toString(source[i]) != "."
                    && Character.toString(source[i]) != "·"
                ) {
                    return ""
                }
            }
            return null
        }
    }

    val inputEmojiFilter = object : InputFilter {
        val emoji = Pattern.compile(
            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE or Pattern.CASE_INSENSITIVE
        )

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            var emojiMatcher = emoji.matcher(source)
            if (emojiMatcher.find()) {
                return ""
            }
            return null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        //ButterKnife.bind(this)
        setPortrait()
        serviceOnLine()
        initCommonTitle()
//        StatusBarUtil.setStatusBarLightMode(this, Color.WHITE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColor(ContextCompat.getColor(this, R.color.white))
        }

        initView()

        AppManager.getInstance().addActivity(this)

    }

    fun setFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //全屏
    }

    fun setPortrait() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
    }

    fun setLandscape() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE// 横屏
    }

    //service 保护
    private fun serviceOnLine() {
//        showLog("PhotoUploadService coming services online")
//        LogUtil.getInstance().correctLogMsg(Constants.FILE_PATH,"logtest.txt",localClassName,Thread.currentThread() .stackTrace[1].methodName,"PhotoUploadService coming services online",true)
        if (!ServiceUtils.isRunService(
                this,
                "com.seatrend.jx.managersystem.service.PhotoUploadService"
            )
        ) {
//            showLog("PhotoUploadService is not run")
//            LogUtil.getInstance().correctLogMsg(Constants.FILE_PATH,"logtest.txt",localClassName,Thread.currentThread() .stackTrace[1].methodName,"PhotoUploadService is not run",true)
//            if (CodeTableSQLiteUtils.queryAll().size > 0) { //只有有数据才去重启服务
//                showLog("PhotoUploadService restart")
//                LogUtil.getInstance().correctLogMsg(Constants.FILE_PATH,"logtest.txt",localClassName,Thread.currentThread() .stackTrace[1].methodName,"PhotoUploadService restart",true)
//                //Android O 启动service
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    startForegroundService(Intent(this, PhotoUploadService::class.java))
//                } else {
//                startService(Intent(this, PhotoUploadService::class.java))
//                }
//            }
        }
    }


    //权限申请
    @RequiresApi(Build.VERSION_CODES.M)
    protected fun appRequestPermissions() {

        val permission = ArrayList<String>()
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.CAMERA)
        }

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (checkSelfPermission(Manifest.permission.INSTALL_PACKAGES) != PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.INSTALL_PACKAGES)
        }
        if (checkSelfPermission(Manifest.permission.NFC) != PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.NFC)
        }
        if (permission.size > 0) {
            ActivityCompat.requestPermissions(this@BaseActivity, permission.toTypedArray(), 1)
        }
    }

    @SuppressLint("WrongViewCast")
    fun initCommonTitle() {
        ivBack = findViewById(R.id.iv_back)
        ivRight = findViewById(R.id.iv_right)
        tvPageTitle = findViewById(R.id.tv_titile)
        tvRight = findViewById(R.id.tv_right)
        rlParent = findViewById(R.id.rl_parent)

        if (ivBack != null) {
            ivBack!!.setOnClickListener { finish() }
        }

    }

    fun hideBackIcon() {
        if (ivBack != null) {
            ivBack!!.visibility = View.GONE
        }
    }

    fun setPageTitle(pageTitle: String) {
        if (tvPageTitle != null) {
            tvPageTitle!!.text = pageTitle
        }
    }

    fun setRightTitle(rightTitle: String) {
        //tvRight
        if (tvRight != null) {
            tvRight!!.text = rightTitle
        }
    }

    /*private void showNetWorkError(){
        if(rlParent==null){
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_network_erroe, null);
        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popmenu_animation);
        try {
            popupWindow.showAsDropDown(rlParent, 0, 0);
        }catch (Exception e){
           e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                });
            }
        }).start();
    }*/


    protected fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT in 12..18) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }

    override fun showToast(msg: Any) {
        ToastUtil.show(this, msg.toString())
    }

    override fun showToast(msgId: Int) {
        ToastUtil.show(this, ""+msgId)
    }

    override fun showErrorDialog(msg: String) {

        val tipsMsg = try {
            GsonUtils.gson(msg, MessageEntity::class.java).message
        } catch (e: java.lang.Exception) {
            msg
        }

        try {
            val mDialog = Dialog(this)
            mDialog.setContentView(R.layout.dialog_error)
            mDialog.setCanceledOnTouchOutside(true)
            mDialog.window!!.setDimAmount(0f);//笼罩层的设计
            val tvMsg = mDialog.findViewById<TextView>(R.id.tv_msg)
            val btnOk = mDialog.findViewById<Button>(R.id.btn_ok)
            val iv_cancel = mDialog.findViewById<ImageView>(R.id.iv_cancel)
            tvMsg.text = tipsMsg
            btnOk.setOnClickListener { mDialog.dismiss() }
            iv_cancel.setOnClickListener { mDialog.dismiss() }
            mDialog.show()
        } catch (e: Exception) {
            showToast("showErrorDialog has Exception")
        }
    }

    fun showWarningMsg(msg: String, title: String) {
        /*final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_warning);
        TextView tvTitle=dialog.findViewById(R.id.tv_title);
        TextView tvMsg=dialog.findViewById(R.id.tv_msg);
        Button tvOk=dialog.findViewById(R.id.tv_ok);
        tvTitle.setText(StringUtils.isNull(title));
        tvMsg.setText(StringUtils.isNull(msg));
        dialog.show();
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

    }

    abstract fun initView()
    abstract fun getLayout(): Int

    override fun onDestroy() {
        super.onDestroy()
        // backHomeThread.interrupt();
        AppManager.getInstance().finishActivity(this)

    }

    protected fun setLightStatusBar(view: View, activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            activity.window.statusBarColor = Color.GRAY
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected fun setStatusBarColor(statusColor: Int) {
        val window = window
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = statusColor
        //设置系统状态栏处于可见状态
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //让view不根据系统窗口来调整自己的布局
        val mContentView = window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val mChildView = mContentView.getChildAt(0)
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false)
            ViewCompat.requestApplyInsets(mChildView)
        }
    }

    fun showNoDataView(msg: String) {

        noDataView = LayoutInflater.from(this).inflate(R.layout.common_no_data, null)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val tvMsg = noDataView!!.findViewById<TextView>(R.id.tv_msg)
        tvMsg.text = msg
        addContentView(noDataView, params)
    }

    fun hideNoDataView() {
        if (noDataView != null) {
            (noDataView!!.parent as ViewGroup).removeView(noDataView)
            noDataView = null
        }

    }

    fun startRotateAlphaAcaleAnimation() {
        overridePendingTransition(R.anim.publish_life_in, R.anim.out_to_left)
    }

    fun exitRotateAlphaAcaleAnimation() {
        overridePendingTransition(R.anim.finish_in, R.anim.publish_life_out)
    }

    fun goNfcReadPlugin(code: Int) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.component = ComponentName(
                "com.seatrend.cd.nfcread",
                "com.seatrend.cd.nfcread.IdCardReadActivity"
            )
            startActivityForResult(intent, code)
        } catch (e: Exception) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.component =
                    ComponentName("com.tmri.cd.nfcread", "com.tmri.cd.nfcread.IdCardReadActivity")
                startActivityForResult(intent, code)
            } catch (e: Exception) {
                showToast("未找到NFC身份证读取插件，请先安装插件")
            }

        }
    }

    private fun getPicFromCamera(code: Int) {
        //用于保存调用相机拍照后所生成的文件
        val tempFile = File(IDCARDIMAGEPATH)//,
        val imageUri: Uri
        if (!tempFile.exists()) {
            tempFile.mkdirs()
        }
        imgOCRFile = File(tempFile, System.currentTimeMillis().toString() + ".jpg")
        if (Build.VERSION.SDK_INT >= 24) {//判断版本
            imageUri = FileProvider.getUriForFile(this, getString(R.string.authority), imgOCRFile!!)
        } else {
            imageUri = Uri.fromFile(imgOCRFile)
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, Integer.toHexString(code).toInt()) //为什么要转16进制，方便在回到界面和nfc区分
    }

    //人脸识别的path 方便和ocr区别，重新定义了一个
    protected fun getFaceCamera(code: Int) {
        //用于保存调用相机拍照后所生成的文件
        val tempFile = File(FACEPATH)
        val imageUri: Uri
        if (!tempFile.exists()) {
            tempFile.mkdirs()
        }
        imgFaceFile = File(tempFile, System.currentTimeMillis().toString() + ".jpg")
        if (Build.VERSION.SDK_INT >= 24) {//判断版本
            imageUri =
                FileProvider.getUriForFile(this, getString(R.string.authority), imgFaceFile!!)
        } else {
            imageUri = Uri.fromFile(imgFaceFile)
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, code)
    }

    protected fun showLog(s: Any) {
        LogUtil.getInstance(this).d(s.toString())
    }

    /**
     * 30分钟无操作推出登录
     */
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
//            MotionEvent.ACTION_UP -> {
//                if (SystemClock.uptimeMillis() - currentTime > LIMIT_TIME) {
//                    AppManager.getInstance().finishToOne(LoginMainActivity::class.java)
//                    currentTime = SystemClock.uptimeMillis()
//                } else {
//                    currentTime = SystemClock.uptimeMillis()
//                }
//            }
        }
        return super.dispatchTouchEvent(event)
    }

    fun showTimeDialog(view: TextView) {
        val dialog = Dialog(this)
        // MAlertDialog dialog=new MAlertDialog(this);
        dialog.setContentView(R.layout.dialog_date_picker)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)
        val btnOk = dialog.findViewById<Button>(R.id.btn_ok)
        val mDatePicker = dialog.findViewById<DatePicker>(R.id.m_date_picker)

        btnCancel.setOnClickListener { dialog.dismiss() }

        btnOk.setOnClickListener {
            val year = mDatePicker.year
            val month = mDatePicker.month + 1
            val dayOfMonth = mDatePicker.dayOfMonth
            val time = "$year-$month-$dayOfMonth"
            val dateToStamp = StringUtils.dateToStamp(time)
            if (dateToStamp - System.currentTimeMillis() > 0) {
                Toast.makeText(this, "日期不能超过当前日期", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                view.text = time
                dialog.dismiss()
            }
        }
    }

    fun showTipDialog(titleS: String?, contentS: String, flag: Int) {
        val dialog = Dialog(this)
        // MAlertDialog dialog=new MAlertDialog(this);
        dialog.setContentView(R.layout.dialog_tip_picker)
        dialog.setCanceledOnTouchOutside(false)

        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)
        val btnOk = dialog.findViewById<Button>(R.id.btn_ok)
        val title = dialog.findViewById<TextView>(R.id.title)
        val content = dialog.findViewById<TextView>(R.id.content)
        if (TextUtils.isEmpty(titleS)) {
            title.visibility = View.GONE
        } else {
            title.visibility = View.VISIBLE
        }
        content.text = contentS
        dialog.show()
        btnCancel.setOnClickListener {
            dialog.dismiss()
            mDialogListenr!!.tipDialogNOListener(flag)
        }
        btnOk.setOnClickListener {
            dialog.dismiss()
            mDialogListenr!!.tipDialogOKListener(flag)
        }
    }

    /**
     * OCR 和NFC的读取
     */
    fun showIcCardReadModeDialog(code: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_idcard_picker)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
        val btn_ocr = dialog.findViewById<Button>(R.id.btn_ocr)
        val btn_nfc = dialog.findViewById<Button>(R.id.btn_nfc)
        btn_ocr.setOnClickListener {
            dialog.dismiss()
            getPicFromCamera(code)
        }
        btn_nfc.setOnClickListener {
            dialog.dismiss()
            goNfcReadPlugin(code)
        }
    }


    private val OCR: Int = 0
    private val NFC: Int = 1

    /**
     * OCR 和NFC的读取
     */
    fun showIcCardReadModePop(position: Int, code: Int) {
        when (position) {
            OCR -> {
                getPicFromCamera(code)
            }
            NFC -> {
                goNfcReadPlugin(code)
            }
        }
    }

    /**
     * OCR相关接口
     */

    fun showLoadingDialog() {
        LoadingDialog.getInstance().showLoadDialog(this)
    }

    fun dismissLoadingDialog() {
        LoadingDialog.getInstance().dismissLoadDialog()
    }

    fun setListener(listenr: DialogListener) {
        mDialogListenr = listenr
    }

    //显示不完全的Textview 处理
    @SuppressLint("ClickableViewAccessibility")
    fun setScollTextView(vararg viewList: TextView) {
        for (view in viewList) {
            view.movementMethod = ScrollingMovementMethod.getInstance()
            val para = view.layoutParams
//            view.maxWidth = DP2PX.dip2px(this,180f)   //最大宽度
//            view.setPadding(0,0,DP2PX.dip2px(this,5f),0)  //padding end 5dp
            view.setOnTouchListener(onTouchListener)
        }
    }

    //显示不完全的Textview 处理(在右端的文字)
    @SuppressLint("ClickableViewAccessibility")
    fun setScollTextView(vararg viewList: TextView, maxWith: Int, paddingEndDis: Int) {
        for (view in viewList) {
            view.movementMethod = ScrollingMovementMethod.getInstance()
            val para = view.layoutParams
            view.maxWidth = DP2PX.dip2px(this, maxWith.toFloat())   //最大宽度
            view.setPadding(0, 0, DP2PX.dip2px(this, paddingEndDis.toFloat()), 0)  //padding end 5dp
            view.setOnTouchListener(onTouchListener)
        }
    }

    //输入表情屏蔽
    fun setEditNoEmoj(vararg editList: EditText) {
        for (view in editList) {
            view.filters = arrayOf(inputFilter)
        }
    }

    //切换大写
    fun setEditUppercase(vararg editList: EditText) {
        for (view in editList) {
            view.transformationMethod = CarHphmUtils.TransInformation()
        }
    }

    //切换fragment(适合两个) 注意（R.id.carmsg_fl）[显示还可以，但是数据传递 有问题哦]
//        fun switchFragment(fragment: Fragment?) {
//            supportFragmentManager.beginTransaction().replace(R.id.carmsg_fl, fragment!!).commit()
//        }

    //设置checkbox 默认值
    fun setCheckBoxDefault(vararg viewList: CheckBox, default: Boolean) {
        for (view in viewList) {
            view.isChecked = default
        }
    }

    //设置不可编辑和点击
    @SuppressLint("ResourceAsColor")
    fun setNoEdit(vararg viewList: View) {
        for (view in viewList) {
            view.isFocusable = false
            view.isEnabled = false
            view.isClickable = false
            if (view is EditText) {
                view.setTextColor(R.color.gray)
            }

            if (view is TextView) {
                view.setTextColor(R.color.gray)
            }

            if (view is Spinner) {
                view.setSelection(0, true)//防止自动被按 https://blog.csdn.net/yangtao6888/article/details/9378215
                view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        view: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        var txtvwSpinner: TextView = view!!.findViewById(R.id.text1)
                        txtvwSpinner.setTextColor(R.color.gray)
                    }
                }
            }
        }
    }


    @SuppressLint("RestrictedApi")
    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        if (checkDoubleClick(intent!!)) {
            super.startActivityForResult(intent, requestCode, options)
        }
    }

    /**
     * 检查是否重复跳转，不需要则重写方法并返回true(这样的好处是界面事件的唯一性，如果同一界面是卡时间，如果不同界面时间没限制)
     *
     * 这是一个解决方案，方案弊端如果是app只有一个activity,fragment切换不适用，方案采用第二个，查验终端的方案
     *
     * 最终两者结合
     */
    private fun checkDoubleClick(intent: Intent): Boolean {

        // 默认检查通过
        var result = true
        // 标记对象
        val tag: String?
        if (intent.component != null) { // 显式跳转
            tag = intent.component!!.className
        } else if (intent.action != null) { // 隐式跳转
            tag = intent.action
        } else {
            return true
        }

        //间隔时间太短 不能跳转  返回false
        if (mActivityJumpTag == tag && LIMIT_CLICK_TIME >= SystemClock.uptimeMillis() - mClickTime!!) {
            return false
        }

        // 间隔时间不短，但是栈里面已经start界面，属于repeat活动界面，返回false[适用于Activity 活动，但是不适用隐式跳转] ---- 堆栈存储的是Activity
//        if(intent.component != null && AppManager.getInstance().repeatActivity(intent.component.className)){
//            return false
//        }

        // 记录启动标记和时间
        mActivityJumpTag = tag
        mClickTime = SystemClock.uptimeMillis()
        return result
    }

    //request
    fun appPermissionReq() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            appRequestPermissions()
        }
    }

    //get all permissions is  OK ?
    fun appGetPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission = ArrayList<String>()
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
//                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    permission.add(Manifest.permission.CAMERA)
//                }
//
//                if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                    permission.add(Manifest.permission.READ_PHONE_STATE)
//                }
//                if (checkSelfPermission(Manifest.permission.NFC) != PackageManager.PERMISSION_GRANTED) {
//                    permission.add(Manifest.permission.NFC)
//                }

            if (permission.size > 0) {
                return false
            }
        }
        return true
    }

    fun sendToTopActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    /**
     * 设置光标的位置（text有值）
     */
    fun setSelection(edit: EditText) {
        if (edit.text != null && edit.text.toString() != null) {
            edit.setSelection(edit.text.toString().length)
        }
    }

    /**
     * 获得资源文字id
     */
    fun getString(i: Int?): String {
        return resources.getString(i!!)
    }

    /**
     * 显示view
     */
    protected fun showView(view: View?) {
        if (view != null && view.visibility != View.VISIBLE) {
            view.visibility = View.VISIBLE
        }
    }

    /**
     * 显示view
     */
    protected fun showView(vararg views: View?) {
        for(view in views) {
            if (view != null && view.visibility != View.VISIBLE) {
                view.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 消失view
     */
    protected fun dissMissView(view: View?) {
        if (view != null && view.visibility != View.GONE) {
            view.visibility = View.GONE
        }
    }

    /**
     * 消失view
     */
    protected fun dissMissView(vararg views: View?) {
        for(view in views){
            if (view != null && view.visibility != View.GONE) {
                view.visibility = View.GONE
            }
        }
    }
    open fun netWorkTaskfailed(commonResponse: CommonResponse?) {
        showErrorDialog(commonResponse!!.getResponseString())
    }

    open fun downloadProgress(commonProgress: CommonProgress?) {
    }
    /**
     * 判断当前线程是否是主线程
     * true  是主线程
     */
    fun isMainThread(): Boolean {
        return Looper.getMainLooper() === Looper.myLooper()
    }

    interface DialogListener {
        fun tipDialogOKListener(flag: Int)
        fun tipDialogNOListener(flag: Int)
    }
}
