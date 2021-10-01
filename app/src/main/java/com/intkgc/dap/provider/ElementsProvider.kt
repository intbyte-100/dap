package com.intkgc.dap.provider

import com.intkgc.dap.page.Element

interface ElementsProvider {
    fun parse(pageMarkdown: String)
    val elementsList: MutableList<Element>
}