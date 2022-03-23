package com.sk.cloudstorage.features.login

import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import com.sk.cloudstorage.base.BasePresenter
import com.sk.cloudstorage.constants.MMKVConstants
import com.sk.cloudstorage.features.login.model.LoginModel
import com.tencent.mmkv.MMKV

/**
 * login业务
 */
class LoginPresenter : BasePresenter() {
    /**
     * 登录成功
     */
    var loginSuccess: () -> Unit = {}

    /**
     * 登录
     */
    fun login(username: String, password: String) {
        WaitDialog.show("登录中")
        LoginModel.login(username, password) {
            MMKV.defaultMMKV().putString(MMKVConstants.USERNAME, username)
            MMKV.defaultMMKV().putString(MMKVConstants.PASSWORD, password)
            TipDialog.show("登录成功")
            loginSuccess()
        }
    }
}