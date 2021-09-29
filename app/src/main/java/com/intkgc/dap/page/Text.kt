package com.intkgc.dap.page

class Text(var text: String = "", vararg var style: TextStyle = arrayOf(TextStyle.normal)) : Element() {

    override fun inflate(builder: PageBuilder) {
        builder.addText(text, *style)
    }

}