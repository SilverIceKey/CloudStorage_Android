package com.sk.cloudstorage.constants

import com.sk.skextension.utils.net.retrofit.RetrofitConfig

/**
 * 服务器配置
 */
class ServerConfig : RetrofitConfig(Constant.SERVER_URL()) {
    override fun isTokenShouldUpdate(): Boolean {
        return false
    }
}