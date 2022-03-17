package com.sk.cloudstorage.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.drake.statusbar.immersive
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 基类公用处理模块
 */
abstract class BaseActivity<T : BaseView> : AppCompatActivity(), ViewInterface {
    lateinit var log: Logger
    lateinit var view: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLog()
        view = newInstanceView()
        view.viewInterface = this
        setContent { view.Content() }
        immersive()
        initAndLoader()
    }

    /**
     * 设置通用log
     */
    private fun setLog() {
        log = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * 让继承的子类都实现初始化实例界面的方法
     */
    abstract fun newInstanceView(): T

    /**
     * 开始初始化类和加载相关参数
     */
    abstract fun initAndLoader()
}