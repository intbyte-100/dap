package com.intkgc.dap.activities

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
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
import com.intkgc.dap.provider.TestElementsProvider

class DocsPageFragment : Fragment(), PageBuilder {
    private lateinit var layout: LinearLayout
    lateinit var elementsProvider: ElementsProvider


    override fun addText(text: String, vararg style: TextStyle) {
        val textView = TextView(this.context)
        textView.text = text
        layout.addView(textView)

        style.forEach {
            when(it){
                TextStyle.BOLD -> textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
                TextStyle.ITALIC -> textView.setTypeface(Typeface.DEFAULT,
                    if(textView.typeface.isBold)
                        Typeface.BOLD_ITALIC
                    else
                        Typeface.ITALIC)
                TextStyle.NORMAL -> textView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
                TextStyle.SMALL -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12.5f)
                TextStyle.MEDIUM -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17.5f)
                TextStyle.HUGE -> textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26.5f)
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
        elementsProvider = TestElementsProvider()

        elementsProvider.elementsList.forEach {
            it.inflate(this)
        }

        return view
    }
}