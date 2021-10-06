package com.intkgc.dap.activities

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView

import android.widget.TableLayout
import android.widget.TableRow

import android.widget.LinearLayout

import android.widget.TextView
import androidx.fragment.app.Fragment
import com.intkgc.dap.R
import com.intkgc.dap.page.PageBuilder
import com.intkgc.dap.page.Text
import com.intkgc.dap.page.TextStyle
import com.intkgc.dap.provider.ElementsProvider
import com.intkgc.dap.provider.markdown.MarkdownElementsProvider
import com.intkgc.dap.util.Dp
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
    private lateinit var layout: TableRow
    private lateinit var tableLayout: TableLayout
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
                TextStyle.BOLD_ITALIC -> textView.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD_ITALIC
                )
                TextStyle.NORMAL -> textView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
                TextStyle.SMALL -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16.5f)
                TextStyle.MEDIUM -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19.5f)
                TextStyle.HUGE -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22.5f)
                TextStyle.SMALL_HEADER -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23.5f)
                TextStyle.MEDIUM_HEADER -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25.5f)
                TextStyle.HUGE_HEADER -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28.5f)
                TextStyle.STRIKETHROUGH -> TODO("add strikethrough support :moyai:")
            }
        }

        textView.layoutParams = TableRow.LayoutParams().apply {
            width = Dp(10).px.property
            weight = 1f
        }

        textView.setTextIsSelectable(true)
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
        tableLayout.addView(scrollView)
    }

    override fun row() {
        layout = TableRow(context)
        tableLayout.addView(layout)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.docs_fragment, null)
        tableLayout = view.findViewById(R.id.docs_fragment_linear_layout)
        elementsProvider = MarkdownElementsProvider()

        elementsProvider.parse(markdown)
        row()

        elementsProvider.elementsList.forEach {
            it.inflate(this)
            if (it.onNextRow)
                row()
        }
        return view
    }
}