package com.sk.cloudstorage.server

import com.sk.cloudstorage.constants.Constant
import com.sk.cloudstorage.constants.GlobalParams
import com.sk.cloudstorage.constants.MMKVConstants
import com.sk.skextension.utils.net.retrofit.RetrofitConfig
import com.tencent.mmkv.MMKV

class ServerConfig : RetrofitConfig(Constant.SERVER_URL()) {

    override fun defaultHeaders(): MutableMap<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Authentication"] = GlobalParams.token
        return headers
    }

    override fun defaultParams(): MutableMap<String, String> {
        val params = mutableMapOf<String, String>()
        return params
    }

    override fun isTokenShouldUpdate(): Boolean {
        return System.currentTimeMillis() - MMKV.defaultMMKV()
            .getLong(MMKVConstants.TOKEN_GET_TIME, 0L) > 60 * 60 * 24 * 1000L
    }
}