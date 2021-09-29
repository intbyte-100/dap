package com.intkgc.dap.page

class CodePanel(var code: String = "", var language: String = "") : Element() {

    override fun inflate(builder: PageBuilder) {
        builder.addCodePanel(code, language)
    }

}