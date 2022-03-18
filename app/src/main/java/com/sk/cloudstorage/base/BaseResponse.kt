package com.sk.cloudstorage.base

/**
 * 基础返回数据类型
 */
open class BaseResponse<T> {
    var code: Int? = null
    var msg: String? = null
    var data: T? = null
}