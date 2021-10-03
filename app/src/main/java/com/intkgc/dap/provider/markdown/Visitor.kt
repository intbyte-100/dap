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
            "_", "*" ->
                if (emphasis.firstChild is org.commonmark.node.Text) {
                    val text = emphasis.firstChild as org.commonmark.node.Text
                    elementsList += Text(text.literal, TextStyle.ITALIC, TextStyle.SMALL)
                }
        }
        super.visit(emphasis)
    }

    override fun visit(emphasis: StrongEmphasis) {
        val text = emphasis.firstChild as org.commonmark.node.Text
        when (emphasis.openingDelimiter) {
            "**", "__" -> elementsList +=
                if (emphasis.parent is Emphasis && (emphasis.parent as Emphasis).openingDelimiter == "*")
                    Text(text.literal, TextStyle.BOLD_ITALIC, TextStyle.SMALL)
                else
                    Text(text.literal, TextStyle.BOLD, TextStyle.SMALL)
        }
        visitChildren(emphasis)
    }

    override fun visit(heading: Heading) {
        val text = heading.firstChild as org.commonmark.node.Text

        var size: TextStyle = TextStyle.HUGE_HEADER

        when (heading.level) {
            1 -> size = TextStyle.HUGE_HEADER
            2 -> size = TextStyle.MEDIUM_HEADER
            3 -> size = TextStyle.SMALL_HEADER
            4 -> size = TextStyle.HUGE
            5 -> size = TextStyle.MEDIUM
            6 -> size = TextStyle.SMALL
        }

        elementsList += Text(text.literal, size, TextStyle.BOLD)
        visitChildren(heading)
    }

    override fun visit(text: org.commonmark.node.Text) {
        if (text.parent is Paragraph)
            elementsList += Text(text.literal)
        visitChildren(text)
    }

    override fun visit(code: Code) {
        elementsList += CodePanel(code.literal)
        super.visit(code)
    }

    override fun visit(fencedCodeBlock: FencedCodeBlock) {
        elementsList += CodePanel(fencedCodeBlock.literal, fencedCodeBlock.info)
        super.visit(fencedCodeBlock)
    }
}
