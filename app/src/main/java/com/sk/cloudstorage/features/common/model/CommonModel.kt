package com.sk.cloudstorage.features.common.model

import com.sk.cloudstorage.constants.MMKVConstants
import com.sk.cloudstorage.features.common.vo.UpdateTokenVo
import com.sk.cloudstorage.server.Api
import com.sk.skextension.utils.net.retrofit.RetrofitClient
import com.sk.smartdevicemanager.utils.http.ResponseCallback
import com.tencent.mmkv.MMKV
import retrofit2.Call

/**
 * 通用网络数据处理
 */
object CommonModel {
    /**
     * 更新Token
     */
    fun updateToken(){
        RetrofitClient.instance
            .createService(Api::class.java)
            .updateToken()
            .enqueue(object : ResponseCallback<UpdateTokenVo>(){
                override fun onResponseSuccess(call: Call<UpdateTokenVo>, response: UpdateTokenVo) {
                    MMKV.defaultMMKV().putLong(MMKVConstants.TOKEN_GET_TIME,System.currentTimeMillis())
                }
            })
    }
}