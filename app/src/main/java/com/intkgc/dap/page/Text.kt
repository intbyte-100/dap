package com.intkgc.dap.page

class Text(var text: String = "", vararg var style: TextStyle = arrayOf(TextStyle.NORMAL, TextStyle.SMALL)) : Element() {
    override fun inflate(builder: PageBuilder) {
        builder.addText(text, *style)
    }
}