package com.sk.cloudstorage.base

/**
 * 基础返回数据类型
 */
open class BaseResponse<T> {
    var errcode: Int? = null
    var errmsg: String? = null
    var data: T? = null
}