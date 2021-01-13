package com.seatrend.utilsdk.ui.common

import java.util.*

/**
 * Created by seatrend on 2018/8/20.
 */

interface BaseView {
    fun showToast(msg: Any)
    fun showToast(msgId: Int)
    fun showErrorDialog(msg: String)
}
