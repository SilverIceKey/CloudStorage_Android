package com.sk.cloudstorage.base

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.KeyboardUtils
import com.sk.cloudstorage.utils.event.EventBean
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.stream.IntStream


/**
 * 基类公用处理模块
 */
abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    lateinit var log: Logger
    var messenger: Messenger? = null
    var serviceConn: ServiceConnection? = null
    var serviceMessenger: Messenger? = null
    var isServiceBind: Boolean = false
    protected var viewDataBinding: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLog()
        //转为databinding
//        setContentView(getLayoutId())
        viewDataBinding = DataBindingUtil.setContentView<T>(this, getLayoutId())
        setupUI(window.decorView)
    }

    override fun onStart() {
        super.onStart()
        initAndLoader()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        if (isUnbindServiceOnDestoryAuto()) {
            log.info("解绑服务")
            unbindService()
        }
        super.onDestroy()
    }

    /**
     * 设置通用log
     */
    private fun setLog() {
        log = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * 循环设置点击事件实现点击空白隐藏软键盘
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun setupUI(view: View) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText && !checkIdInExcludeUI(view.id)) {
            view.setOnTouchListener { v, event ->
                KeyboardUtils.hideSoftInput(this)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    /**
     * 不隐藏键盘的ui按照id排除
     */
    open fun hideKeyboardIdExclude(): IntArray {
        return intArrayOf()
    }

    /**
     * 检测id是否排除
     */
    private fun checkIdInExcludeUI(id: Int): Boolean {
        return IntStream.of(*hideKeyboardIdExclude()).anyMatch { t -> t == id }
    }

    /**
     * service消息处理，如果创建service时重写
     * 消息下发到viewcontrol，只有显示的viewcontrol能收到消息
     */
    open fun handleMessageFromService(msg: Message) {

    }

    /**
     * 绑定服务方法，在绑定服务之前创建messenger
     */
    fun bindService(service: Intent?): Boolean {
        createMessenger()
        createServiceConnection()
        return super.bindService(service, serviceConn!!, Context.BIND_AUTO_CREATE)
    }

    /**
     * 创建messenger，当调用绑定服务时
     */
    private fun createMessenger() {
        messenger = Messenger(object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                handleMessageFromService(msg = msg)
            }
        })
    }

    /**
     * 创建服务绑定监听
     */
    private fun createServiceConnection() {
        serviceConn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                log.info("绑定服务成功")
                serviceMessenger = Messenger(service)
                isServiceBind = true
                serviceConnected()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                log.info("解绑服务成功")
                isServiceBind = false
                serviceMessenger = null
                messenger = null
                serviceConn = null
            }

        }
    }

    /**
     * 给服务端发送消息
     */
    fun sendMessage(messageBundle: Bundle) {
        if (!isServiceBind) {
            log.warn("服务暂未绑定,请稍后发送消息")
        }
        val message = Message()
        message.replyTo = messenger
        message.data = messageBundle
        serviceMessenger?.send(message)
    }

    /**
     * 服务绑定之后
     */
    open fun serviceConnected() {

    }

    /**
     * 解绑服务
     */
    fun unbindService() {
        if (isServiceBind) {
            super.unbindService(serviceConn!!)
        }
    }

    /**
     * 是否在销毁时自动解绑服务
     */
    open fun isUnbindServiceOnDestoryAuto(): Boolean = true

    /**
     * Eventbus通知接收
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun receiveEvent(data: EventBean<*>) {
        handleEvent(data)
    }

    /**
     * Eventbus通知处理
     */
    open fun handleEvent(data: EventBean<*>) {

    }

    /**
     * 获取界面资源文件
     */
    abstract fun getLayoutId(): Int

    /**
     * 开始初始化类和加载相关参数
     */
    abstract fun initAndLoader()
}