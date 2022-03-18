package com.sk.cloudstorage.features.login.model

import com.sk.cloudstorage.constants.GlobalParams
import com.sk.cloudstorage.constants.MMKVConstants
import com.sk.cloudstorage.server.Api
import com.sk.skextension.utils.net.retrofit.RetrofitClient
import com.sk.cloudstorage.utils.http.ResponseCallback
import com.tencent.mmkv.MMKV
import retrofit2.Call

/**
 * 登录网络请求
 */
object LoginModel {
    /**
     * 登录
     */
    fun login(username: String, password: String,onSuccess:()->Unit) {
        val params = hashMapOf<String,String>()
        params["username"] = username
        params["password"] = password
        RetrofitClient
            .instance
            .createService(Api::class.java)
            .login(params = params)
            .enqueue(object : ResponseCallback<LoginResponse>(){
                override fun onResponseSuccess(call: Call<LoginResponse>, response: LoginResponse) {
                    GlobalParams.token = response.data?.token!!
                    MMKV.defaultMMKV().putLong(MMKVConstants.TOKEN_GET_TIME,System.currentTimeMillis())
                    onSuccess()
                }
            })
    }
}