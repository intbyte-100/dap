package com.intkgc.dap.provider.markdown

import com.intkgc.dap.page.Element
import com.intkgc.dap.provider.ElementsProvider
import org.commonmark.Extension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension
import org.commonmark.ext.task.list.items.TaskListItemsExtension
import org.commonmark.node.Node
import org.commonmark.parser.Parser

class MarkdownElementsProvider : ElementsProvider {

    override fun parse(pageMarkdown: String) {
        val extensions: List<Extension> =
            listOf(TaskListItemsExtension.create(), StrikethroughExtension.create())
        val parser: Parser = Parser.builder().extensions(extensions).build()
        val document: Node = parser.parse(pageMarkdown)
        val visitor = Visitor()
        document.accept(visitor)
        elementsList = visitor.elementsList
    }

    override var elementsList: MutableList<Element> = ArrayList()
}