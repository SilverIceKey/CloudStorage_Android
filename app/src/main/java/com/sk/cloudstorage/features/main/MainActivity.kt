package com.sk.cloudstorage.features.main

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.sk.skextension.getString
import com.sk.skextension.utils.net.mqtt.EMQXHelper
import com.sk.skextension.utils.receivers.ScreenStatusReceiver
import com.sk.cloudstorage.base.BaseActivity
import com.sk.cloudstorage.constants.Constant
import com.sk.cloudstorage.features.login.LoginActivity

/**
 * 主界面activity
 */
class MainActivity : BaseActivity<MainPresenter, MainView>() {

    override fun newInstanceView(presenter: MainPresenter): MainView = MainView(presenter)

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initAndLoader() {
        checkStoragePermission()
        ScreenStatusReceiver.registerReceiver(this)
        presenter.logout = {
            val logoutIntent = Intent(this, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ActivityUtils.startActivity(logoutIntent)
            finish()
        }
    }

    /**
     * 获取存储读写权限
     */
    private fun checkStoragePermission() {
        if (!PermissionUtils.isGranted(*Constant.resquestRWStoragePermission)) {
            log.info("${Constant.resquestRWStoragePermission} 权限未授权开始请求")
            PermissionUtils.permission(*Constant.resquestRWStoragePermission)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        log.info("${Constant.resquestRWStoragePermission.getString()} 权限已允许回调")
                    }

                    override fun onDenied() {
                        log.info("${Constant.resquestRWStoragePermission.getString()} 权限已绝回调")
                    }

                }).request()
        } else {
            log.info("${Constant.resquestRWStoragePermission.getString()} 权限已允许")
        }
    }
}