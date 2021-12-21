package com.sk.cloudstorage.base

/**
 * 基础返回数据类型
 */
open class BaseResponse<T> {
    var result: T? = null
    var code: Int = -1
}