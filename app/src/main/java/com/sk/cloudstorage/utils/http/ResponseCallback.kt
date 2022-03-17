package com.sk.smartdevicemanager.utils.http

import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import com.sk.cloudstorage.base.BaseResponse
import com.sk.cloudstorage.constants.ErrorCode
import com.sk.cloudstorage.features.common.model.CommonModel
import com.sk.smartdevicemanager.utils.event.CommonErrorEvent
import org.greenrobot.eventbus.EventBus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 通用返回处理
 */
abstract class ResponseCallback<T : BaseResponse<*>> : Callback<T> {
    var log: Logger

    init {
        //初始化日志工具
        log = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * 服务器200之后对errorcode进行判断处理
     */
    override fun onResponse(call: Call<T>, response: Response<T>) {
        log.info("网络请求成功")
        val responseData = response.body()
        if (responseData?.errcode != 0) {
            onResponseError(responseData?.errcode, responseData?.errmsg)
        } else {
            onResponseSuccess(call, responseData)
        }
    }

    /**
     * 网络异常，无法达到服务器或服务器出现非200情况执行
     */
    override fun onFailure(call: Call<T>, t: Throwable) {
        log.warn("网络数据请求异常", t)
        TipDialog.show("网络数据请求异常", WaitDialog.TYPE.ERROR)
        EventBus.getDefault().post(CommonErrorEvent("网络数据请求异常"))
    }

    /**
     * 请求成功之后错误码非错误执行
     */
    abstract fun onResponseSuccess(call: Call<T>, response: T)

    /**
     * 请求成功之后错误码有错误执行
     */
    open fun onResponseError(errorCode: Int?, errorMsg: String?) {
        log.info("请求错误,errcode:${errorCode},原因:${errorMsg}")
        if (errorCode == ErrorCode.TOKEN_ERROR) {
            CommonModel.updateToken()
        } else {
            TipDialog.show(errorMsg, WaitDialog.TYPE.ERROR)
        }
    }
}