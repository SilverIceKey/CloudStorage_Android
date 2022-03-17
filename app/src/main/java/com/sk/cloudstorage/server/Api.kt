package com.sk.cloudstorage.server

import com.sk.cloudstorage.features.common.vo.UpdateTokenVo
import retrofit2.Call
import retrofit2.http.GET

/**
 * 请求接口
 */
interface Api {
    /**
     * 更新Token
     */
    @GET("user/updateToken")
    fun updateToken():Call<UpdateTokenVo>
}