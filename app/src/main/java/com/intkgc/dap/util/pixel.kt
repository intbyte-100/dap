package com.intkgc.dap.util


import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt


private var xdpi = 0f

@JvmInline
value class Dp(val property: Int){
    val px: Px
        get() = Px((property * (xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt())
}

@JvmInline
value class Px(val property: Int){
    val dp: Dp
        get() = Dp((property / (xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt())
}




fun updateContext(context: Context){
    xdpi = context.resources.displayMetrics.xdpi
}
