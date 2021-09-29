package com.intkgc.dap.page

interface PageBuilder {
    fun addText(text: String, vararg style: TextStyle)
    fun addCodePanel(code: String, language: String)
}