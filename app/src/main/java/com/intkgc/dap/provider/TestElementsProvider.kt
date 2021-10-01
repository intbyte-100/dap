package com.intkgc.dap.provider

import com.intkgc.dap.page.Element
import com.intkgc.dap.page.Text

class TestElementsProvider() : ElementsProvider {

    override val elementsList: MutableList<Element> = arrayListOf(Text("hello"), Text("world!"))



    override fun parse(pageMarkdown: String) {
        TODO("Not yet implemented")
    }
}