package com.sk.cloudstorage.features.login

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.sk.cloudstorage.base.BaseActivity
import com.sk.cloudstorage.features.main.MainActivity

/**
 * 登录
 */
class LoginActivity : BaseActivity<LoginPresenter, LoginView>() {

    override fun newInstanceView(presenter: LoginPresenter): LoginView = LoginView(presenter)

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initAndLoader() {
        presenter.loginSuccess = {
            ActivityUtils.startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}