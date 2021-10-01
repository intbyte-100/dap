package com.intkgc.dap.page

class Text(var text: String = "", vararg var style: TextStyle = arrayOf(TextStyle.NORMAL)) : Element() {

    override fun inflate(builder: PageBuilder) {
        builder.addText(text, *style)
    }

}