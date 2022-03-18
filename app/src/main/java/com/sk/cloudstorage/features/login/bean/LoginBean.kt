package com.sk.cloudstorage.features.login.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sk.cloudstorage.BR

/**
 * 登录界面参数
 */
class LoginBean : BaseObservable() {
    /**
     * 用户名
     */
    @Bindable
    var username: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.username)
        }

    /**
     * 密码
     */
    @Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}