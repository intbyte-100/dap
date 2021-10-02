package com.intkgc.dap.provider.markdown

import com.intkgc.dap.page.CodePanel
import com.intkgc.dap.page.Element
import com.intkgc.dap.page.Text
import com.intkgc.dap.page.TextStyle
import org.commonmark.node.*

class Visitor : AbstractVisitor() {

    val elementsList: ArrayList<Element> = ArrayList()

    override fun visit(emphasis: Emphasis) {
        when (emphasis.openingDelimiter) {
            "_", "*" -> {
                if (emphasis.firstChild is org.commonmark.node.Text) {
                    val text = emphasis.firstChild as org.commonmark.node.Text
                    elementsList.add(Text(text.literal, TextStyle.ITALIC))
                }
            }
        }
        super.visit(emphasis)
    }

    override fun visit(emphasis: StrongEmphasis) {
        val text = emphasis.firstChild as org.commonmark.node.Text
        when (emphasis.openingDelimiter) {
            "**", "__" -> {
                if (emphasis.parent is Emphasis && (emphasis.parent as Emphasis).openingDelimiter == "*")
                    elementsList.add(Text(text.literal, TextStyle.BOLD_ITALIC))
                else
                    elementsList.add(Text(text.literal, TextStyle.BOLD))
            }
        }
        visitChildren(emphasis)
    }

    override fun visit(heading: Heading) {
        val text = heading.firstChild as org.commonmark.node.Text
        elementsList.add(
                Text(text.literal, TextStyle.HUGE, TextStyle.BOLD)
        )
        visitChildren(heading)
    }

    override fun visit(text: org.commonmark.node.Text) {
        if (text.parent is Paragraph) {
            elementsList.add(
                    Text(text.literal)
            )
        }
        visitChildren(text)
    }

    override fun visit(code: Code) {
        elementsList.add(CodePanel(code.literal))
        super.visit(code)
    }

    override fun visit(fencedCodeBlock: FencedCodeBlock) {
        elementsList.add(CodePanel(fencedCodeBlock.literal, fencedCodeBlock.info))
        super.visit(fencedCodeBlock)
    }
}
