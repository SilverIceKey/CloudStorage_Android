package com.sk.cloudstorage.features.login

import com.sk.cloudstorage.base.BasePresenter
import com.sk.cloudstorage.features.login.model.LoginModel

/**
 * 登录中间件
 */
class LoginPresenter : BasePresenter() {
    /**
     * 登录成功返回
     */
    var onLoginSuccess: () -> Unit = {}

    /**
     * 登录
     */
    fun login(username:String,password:String) {
        LoginModel.login(username, password) {
            onLoginSuccess()
        }
    }
}