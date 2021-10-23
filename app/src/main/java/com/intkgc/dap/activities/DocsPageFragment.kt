package com.intkgc.dap.activities

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView

import android.widget.TableLayout
import android.widget.TableRow

import android.widget.LinearLayout

import android.widget.TextView
import androidx.core.text.getSpans
import androidx.fragment.app.Fragment
import com.intkgc.dap.R
import com.intkgc.dap.page.PageBuilder
import com.intkgc.dap.page.Text
import com.intkgc.dap.page.TextStyle
import com.intkgc.dap.provider.ElementsProvider
import com.intkgc.dap.provider.markdown.MarkdownElementsProvider
import com.intkgc.dap.util.Dp
import com.intkgc.dap.util.StyledString
import com.intkgc.dap.util.setStyle
import com.intkgc.dap.util.updateContext

const val markdown = """
# Header 1
## Header 2
### Header 3
#### Header 4
##### Header 5
###### Header 6
text
__bold__
**bold**
_italic_
*italic*
***bold-italic***
## Some code :
```
print("Hello, World!");
```
- [x] *some TaskListItem*

"""


class DocsPageFragment : Fragment(), PageBuilder {
    private lateinit var layout: LinearLayout
    private val styledString = StyledString()

    private lateinit var elementsProvider: ElementsProvider
    private var lastTextView: TextView? = null


    private fun flush(){
        lastTextView?.text = styledString.toSpannable()
        styledString.clear()
        lastTextView = null
    }
    override fun addText(text: String, vararg style: TextStyle) {
        if(lastTextView == null) {
            val horizontalPadding = Dp(12).px.property
            val verticalPadding = Dp(2).px.property
            val textView = TextView(this.context)
            textView.text = SpannableString("")
            layout.addView(textView)
            textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16.5f)
            lastTextView = textView
        }

        styledString.add("$text\n", *style)


        lastTextView!!.setTextIsSelectable(true)
    }


    override fun addCodePanel(code: String, language: String) {
        val textView = TextView(context)
        val scrollView = HorizontalScrollView(context)
        val horizontalPadding = Dp(17).px.property
        val verticalPadding = Dp(20).px.property

        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14.5f)
        textView.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL)

        textView.text = code

        scrollView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)

        scrollView.setPadding(
            horizontalPadding,
            verticalPadding,
            horizontalPadding,
            verticalPadding
        )

        scrollView.isHorizontalScrollBarEnabled = true
        scrollView.scrollBarSize = Dp(7).px.property
        scrollView.overScrollMode = 2
        scrollView.addView(textView)
        layout.addView(scrollView)
        flush()
    }

    override fun row() {


    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateContext(requireContext())
        val view = inflater.inflate(R.layout.docs_fragment, null)
        layout = view.findViewById(R.id.docs_fragment_linear_layout)
        elementsProvider = MarkdownElementsProvider()
        elementsProvider.parse(markdown)
        row()

        elementsProvider.elementsList.forEach {
            it.inflate(this)
            if (it.onNextRow)
                row()
        }
        flush()
        return view
    }
}