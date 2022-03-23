package com.sk.cloudstorage.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.Utils
import com.sk.skextension.utils.net.retrofit.RetrofitClient
import com.tencent.mmkv.MMKV
import com.sk.cloudstorage.MyApplication
import com.sk.cloudstorage.base.BaseActivity
import com.sk.cloudstorage.features.login.LoginActivity
import com.sk.cloudstorage.features.main.MainActivity
import com.sk.cloudstorage.server.ServerConfig

/**
 * 启动界面
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashPresenter, SplashView>() {

    override fun newInstanceView(presenter: SplashPresenter): SplashView = SplashView(presenter)

    override fun createPresenter(): SplashPresenter {
        return SplashPresenter()
    }

    override fun initAndLoader() {
        //初始化MMKV
        MMKV.initialize(this)
        //初始化工具类
        Utils.init(MyApplication.globalContext)
        //设置网络
        RetrofitClient.instance.setApplicationContext(this)
        RetrofitClient.instance.defaultConfig(ServerConfig())

        ActivityUtils.startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}