package com.sk.cloudstorage.features.main

import com.sk.cloudstorage.R
import com.sk.cloudstorage.base.BaseActivity
import com.sk.cloudstorage.databinding.ActivityMainBinding
import com.sk.skextension.utils.receivers.ScreenStatusReceiver

/**
 * 主界面activity
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initAndLoader() {
        ScreenStatusReceiver.registerReceiver(this)
    }
}