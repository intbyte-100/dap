package com.intkgc.dap.util

import android.text.Spannable
import android.text.SpannableString
import com.intkgc.dap.page.TextStyle
import java.lang.StringBuilder

private class StyledStringFragment(val string: String, val begin: Int, val end: Int, vararg var style: TextStyle)

class StyledString {
    private val styledStrings = mutableListOf<StyledStringFragment>()
    private var length = 0

    fun add(string: String, vararg textStyle: TextStyle){
        styledStrings += StyledStringFragment(string, length, length+string.length, *textStyle)
        length+=string.length
    }

    fun toSpannable(): Spannable {
        val builder = StringBuilder()
        for (i in styledStrings)
            builder.append(i.string)

        val spannable = SpannableString(builder)
        for (i in styledStrings)
            spannable.setStyle(i.begin, i.end, *i.style)

        return spannable
    }


    fun clear() {
        styledStrings.clear()
        length = 0
    }
}