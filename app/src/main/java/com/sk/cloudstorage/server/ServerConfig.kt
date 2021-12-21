package com.sk.cloudstorage.server

import com.sk.cloudstorage.constants.Constant
import com.sk.skextension.utils.net.retrofit.RetrofitConfig

class ServerConfig: RetrofitConfig(Constant.SERVER_URL()) {

    override fun defaultHeaders(): MutableMap<String, String> {
        val headers = mutableMapOf<String,String>()
        headers.put("test-header","test")
        return headers
    }

    override fun defaultParams(): MutableMap<String, String> {
        val params = mutableMapOf<String,String>()
        params.put("test-params","test")
        return params
    }
}