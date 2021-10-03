package com.intkgc.dap.activities

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.intkgc.dap.R
import com.intkgc.dap.page.PageBuilder
import com.intkgc.dap.page.TextStyle
import com.intkgc.dap.provider.ElementsProvider
import com.intkgc.dap.provider.markdown.MarkdownElementsProvider
import com.intkgc.dap.util.Dp
import com.intkgc.dap.util.updateContext

const val markdown = """# Header

**bold**
it really works
**lol**
_italic_

***BOLD ITALIC***"""
class DocsPageFragment : Fragment(), PageBuilder {
    private lateinit var layout: LinearLayout
    lateinit var elementsProvider: ElementsProvider


    override fun addText(text: String, vararg style: TextStyle) {
        updateContext(requireContext())
        val horizontalPadding = Dp(12).px.property
        val verticalPadding = Dp(2).px.property
        val textView = TextView(this.context)
        textView.text = text
        layout.addView(textView)

        textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)

        style.forEach {
            when (it) {

                TextStyle.BOLD -> textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
                TextStyle.ITALIC -> textView.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
                TextStyle.BOLD_ITALIC -> textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
                TextStyle.NORMAL -> textView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
                TextStyle.SMALL -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16.5f)
                TextStyle.MEDIUM -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19.5f)
                TextStyle.HUGE -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22.5f)
            }
        }


    }

    override fun addCodePanel(code: String, language: String) {
        TODO("Not yet implemented")
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.docs_fragment, null)
        layout = view.findViewById(R.id.docs_fragment_linear_layout)
        elementsProvider = MarkdownElementsProvider()

        elementsProvider.parse(markdown)

        layout.gravity = Gravity.CENTER

        elementsProvider.elementsList.forEach {
            it.inflate(this)
        }

        return view
    }
}