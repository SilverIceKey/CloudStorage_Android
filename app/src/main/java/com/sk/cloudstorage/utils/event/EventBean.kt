package com.sk.cloudstorage.utils.event

open class EventBean<T>(
    var msg: String = "",
    var code: Int = 1,
    var data: T? = null,
    var type: String = ""
) {

}