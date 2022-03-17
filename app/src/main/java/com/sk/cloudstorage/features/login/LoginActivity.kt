package com.sk.cloudstorage.features.login

import com.sk.cloudstorage.base.BaseActivity

/**
 * 登录
 */
class LoginActivity:BaseActivity<LoginView>() {
    override fun newInstanceView(): LoginView = LoginView()

    override fun initAndLoader() {

    }
}