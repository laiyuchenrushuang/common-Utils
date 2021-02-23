package com.seatrend.utilsdk.ui.list

/**
 * Created by ly on 2021/1/25 14:51
 */
//RecyclerView 多选的带测量的流式布局
import android.content.Context
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by ly on 2019/11/11 10:53
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class MyRecycleManager(var mContext: Context?) : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler!!)

        val sumWidth = width
        var curLineWidth = 0
        var curLineTop = 0
        var lastLineMaxHeight = 0
        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)

            addView(view)
            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)
            curLineWidth += width
            if (curLineWidth <= sumWidth) {//不需要换行
                layoutDecorated(
                    view,
                    curLineWidth - width,
                    curLineTop,
                    curLineWidth,
                    curLineTop + height
                )
                //比较当前行多有item的最大高度
                lastLineMaxHeight = Math.max(lastLineMaxHeight, height)
            } else {//换行
                curLineWidth = width
                if (lastLineMaxHeight == 0) {
                    lastLineMaxHeight = height
                }
                //记录当前行top
                curLineTop += lastLineMaxHeight

                layoutDecorated(view, 0, curLineTop, width, curLineTop + height)
                lastLineMaxHeight = height
            }
        }
    }
}