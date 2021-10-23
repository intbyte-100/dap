package com.intkgc.dap.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import com.intkgc.dap.page.TextStyle

fun Spannable.setStyle(begin: Int, end: Int, vararg textStyle: TextStyle) {
    var typeFace = 0
    var size = 0f

    textStyle.forEach {
        when (it) {
            TextStyle.BOLD -> typeFace = Typeface.BOLD
            TextStyle.ITALIC -> typeFace = Typeface.ITALIC
            TextStyle.BOLD_ITALIC -> typeFace = Typeface.BOLD_ITALIC
            TextStyle.NORMAL -> typeFace = Typeface.NORMAL
            TextStyle.SMALL -> size = 1f
            TextStyle.MEDIUM -> size = 1.18f
            TextStyle.HUGE -> size = 1.36f
            TextStyle.SMALL_HEADER -> size = 1.42f
            TextStyle.MEDIUM_HEADER -> size = 1.54f
            TextStyle.HUGE_HEADER -> size = 1.72f
            TextStyle.STRIKETHROUGH -> setSpan(StrikethroughSpan(), begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }


    setSpan(RelativeSizeSpan(size), begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    setSpan(StyleSpan(typeFace), begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}