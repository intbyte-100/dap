package com.intkgc.dap.provider.markdown

import com.intkgc.dap.page.Element
import com.intkgc.dap.provider.ElementsProvider
import org.commonmark.node.Node
import org.commonmark.parser.Parser

class MarkdownElementsProvider : ElementsProvider {

    override fun parse(pageMarkdown: String) {
        val parser: Parser = Parser.builder().build()
        val document: Node = parser.parse(pageMarkdown)
        val visitor = Visitor()
        document.accept(visitor)
        elementsList = visitor.elementsList
    }

    override var elementsList: MutableList<Element> = ArrayList()
}