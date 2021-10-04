package com.intkgc.dap.provider.markdown

import com.intkgc.dap.page.*
import org.commonmark.ext.task.list.items.TaskListItemMarker
import org.commonmark.node.*
import org.commonmark.node.Image as CommonmarkNodeImage
import org.commonmark.node.Text as CommonmarkNodeText


class Visitor : AbstractVisitor() {

    val elementsList: ArrayList<Element> = ArrayList()

    override fun visit(emphasis: Emphasis) {
        when (emphasis.openingDelimiter) {
            "_", "*" ->
                if (emphasis.firstChild is CommonmarkNodeText) {
                    val text = emphasis.firstChild as CommonmarkNodeText
                    elementsList += Text(text.literal, TextStyle.ITALIC, TextStyle.SMALL)
                }
        }
    }

    override fun visit(emphasis: StrongEmphasis) {
        val text = emphasis.firstChild as CommonmarkNodeText
        when (emphasis.openingDelimiter) {
            "**", "__" -> elementsList +=
                if (emphasis.parent is Emphasis && (emphasis.parent as Emphasis).openingDelimiter == "*")
                    Text(text.literal, TextStyle.BOLD_ITALIC, TextStyle.SMALL)
                else
                    Text(text.literal, TextStyle.BOLD, TextStyle.SMALL)
        }
    }

    override fun visit(heading: Heading) {
        val text = heading.firstChild as CommonmarkNodeText

        val size = when (heading.level) {
            1 -> TextStyle.HUGE_HEADER
            2 -> TextStyle.MEDIUM_HEADER
            3 -> TextStyle.SMALL_HEADER
            4 -> TextStyle.HUGE
            5 -> TextStyle.MEDIUM
            6 -> TextStyle.SMALL
            else -> throw IllegalArgumentException("Invalid header size")
        }

        elementsList += Text(text.literal, size, TextStyle.BOLD)
    }

    override fun visit(text: CommonmarkNodeText) {
        elementsList += Text(text.literal)
    }

    override fun visit(code: Code) {
        elementsList += CodePanel(code.literal)
    }

    override fun visit(fencedCodeBlock: FencedCodeBlock) {
        elementsList += CodePanel(fencedCodeBlock.literal, fencedCodeBlock.info)
    }

    override fun visit(image: CommonmarkNodeImage) {
        elementsList += Image(image.destination)
    }

    override fun visit(softLineBreak: SoftLineBreak?) {
        super.visit(softLineBreak)
    }

    // Used for debugging
    override fun visitChildren(parent: Node?) {
        println(parent)
        super.visitChildren(parent)
    }
}