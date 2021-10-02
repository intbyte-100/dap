package com.intkgc.dap.provider

import com.intkgc.dap.page.Element
import com.intkgc.dap.page.Text
import com.intkgc.dap.page.TextStyle
import org.commonmark.node.AbstractVisitor
import org.commonmark.node.Heading
import org.commonmark.node.Paragraph
import org.commonmark.node.StrongEmphasis

class Visitor : AbstractVisitor() {

    val elementsList: ArrayList<Element> = ArrayList()

    override fun visit(emphasis: StrongEmphasis?) {
        if (emphasis != null) {
            val text = emphasis.firstChild as org.commonmark.node.Text
            when (emphasis.openingDelimiter) {
                "__" -> {
                    elementsList.add(
                        Text(text.literal, TextStyle.ITALIC)
                    )
                }
                "**" -> {
                    elementsList.add(
                        Text(text.literal, TextStyle.BOLD)
                    )
                }
            }
        }
        visitChildren(emphasis)
    }

    override fun visit(heading: Heading?) {
        if (heading != null) {
            val text = heading.firstChild as org.commonmark.node.Text
            elementsList.add(
                Text(text.literal, TextStyle.HUGE, TextStyle.BOLD)
            )
        }
        visitChildren(heading)
    }

    override fun visit(text: org.commonmark.node.Text?) {
        if (text != null) {
            if (text.parent is Paragraph) {
                elementsList.add(
                    Text(text.literal)
                )
            }
        }
        visitChildren(text)
    }
}
