package com.intkgc.dap.activities

import android.annotation.SuppressLint
import android.os.Bundle
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
    lateinit var elementsProvider: ElementsProvider
    lateinit var layout: LinearLayout

    override fun addText(text: String, vararg style: TextStyle) {
        val textView = TextView(this.context)
        textView.text = text
        layout.addView(textView)
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