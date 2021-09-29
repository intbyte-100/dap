package com.intkgc.dap.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.intkgc.dap.page.PageBuilder
import com.intkgc.dap.page.TextStyle
import com.intkgc.dap.provider.ElementsProvider

class DocumentationPageFragment() : Fragment(), PageBuilder {
    lateinit var elementsProvider: ElementsProvider


    override fun addText(text: String, vararg style: TextStyle) {
        TODO("Not yet implemented")
    }

    override fun addCodePanel(code: String, language: String) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        elementsProvider.elementsList.forEach {
            it.inflate(this)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}