package com.sk.cloudstorage.features.login

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.sk.cloudstorage.R
import com.sk.cloudstorage.base.BaseActivity
import com.sk.cloudstorage.constants.Constant
import com.sk.cloudstorage.databinding.ActivityLoginBinding
import com.sk.cloudstorage.features.login.bean.LoginBean
import com.sk.cloudstorage.features.main.MainActivity
import com.sk.skextension.getString

/**
 * 登录
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    var loginPresenter: LoginPresenter? = null
    var loginBean: LoginBean? = null
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initAndLoader() {
        loginBean = LoginBean()
        loginPresenter = LoginPresenter()
        loginPresenter?.onLoginSuccess = {
            ActivityUtils.startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
        viewDataBinding?.loginBean = loginBean
        viewDataBinding?.loginPresenter = loginPresenter
//        loginBean?.username = "zwj"
//        loginBean?.password = "zwj123"
        checkStoragePermission()
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