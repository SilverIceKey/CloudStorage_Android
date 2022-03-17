package com.sk.cloudstorage.constants

import android.Manifest

object Constant {
    /**
     * 加载so
     */
    init {
        System.loadLibrary("cloudstorage")
    }

    /**
     * 存储器读写权限
     */
    val resquestRWStoragePermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    /***
     * 需要自行创建C++模块
     * 服务器地址 请替换自己的服务器地址
     */
    external fun SERVER_URL(): String

    /**
     *MQTT服务器地址
     */
//    external fun MQTTSERVER_URL(): String

    /**
     * 获取加密密钥
     */
    external fun EncryptKey(): String
}