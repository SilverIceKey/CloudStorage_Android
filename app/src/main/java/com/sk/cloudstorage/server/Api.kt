package com.sk.cloudstorage.server

import com.sk.cloudstorage.features.common.vo.UpdateTokenVo
import com.sk.cloudstorage.features.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 请求接口
 */
interface Api {
    /**
     * 更新Token
     */
    @GET("user/updateToken")
    fun updateToken():Call<UpdateTokenVo>

    /**
     * 登录
     * username
     * password
     */
    @POST("user/login")
    fun login(@Body params:Map<String,String>):Call<LoginResponse>
}