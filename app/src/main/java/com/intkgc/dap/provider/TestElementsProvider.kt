package com.intkgc.dap.provider

import com.intkgc.dap.page.Element
import com.intkgc.dap.page.Text
import com.intkgc.dap.page.TextStyle

class TestElementsProvider() : ElementsProvider {

    override val elementsList: MutableList<Element> = arrayListOf(
        Text("It's work!", TextStyle.HUGE),
        Text("hello", TextStyle.MEDIUM, TextStyle.ITALIC),
        Text("world!", TextStyle.MEDIUM,TextStyle.BOLD_ITALIC))

    override fun parse(pageMarkdown: String) {
        TODO("Not yet implemented")
    }
}