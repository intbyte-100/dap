package com.intkgc.dap.page

abstract class Element {
    open var onNextRow = true
    abstract fun inflate(builder: PageBuilder)
}