package com.sk.cloudstorage.base

import androidx.compose.runtime.Composable
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * view基类
 */
abstract class BaseView<T : BasePresenter>(val presenter: T) {
    //日志
    lateinit var log: Logger

    init {
        setLog()
    }


    /**
     * 设置通用log
     */
    private fun setLog() {
        log = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * 创建界面
     */
    @Composable
    abstract fun Content()
}