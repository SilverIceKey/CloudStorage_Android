package com.sk.cloudstorage

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.sk.skextension.utils.crash.GlobalCrashCatch
import com.sk.skextension.utils.crash.GlobalCrashHandleCallback
import com.sk.skextension.utils.explain.Explain
import com.sk.skextension.utils.net.retrofit.RetrofitClient
import com.sk.cloudstorage.server.ServerConfig

/**
 * App启动时启动，生命周期贯穿app全局
 */
class MyApplication : MultiDexApplication() {
    companion object {
        @Explain(explainValue = "全局Context")
        lateinit var globalContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        globalContext = this
        //全局异常捕捉
        GlobalCrashCatch.instance.init(this)
            .setGlobalCrashHandlerListener(object : GlobalCrashHandleCallback {
                override fun crashHandler(e: Throwable): Boolean {
                    return true
                }
            })
    }
}