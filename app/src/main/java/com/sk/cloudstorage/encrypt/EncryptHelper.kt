package com.sk.cloudstorage.utils.encrypt

import com.sk.skextension.utils.encrypt.EncryptUtil
import com.sk.cloudstorage.constants.Constant

/**
 * 加密帮助类
 */
object EncryptHelper {
    /**
     * AES加密(AES_ECB_PKCS5)
     */
    fun AESEncode(content:String):String{
        return EncryptUtil.AESEncode(key = Constant.EncryptKey(), content = content)
    }

    /**
     * AES解密(AES_ECB_PKCS7)
     */
    fun AESDecode(content:String):String{
        return String(EncryptUtil.AESDecode(key = Constant.EncryptKey(), content = content))
    }

    /**
     * MD5加密
     */
    fun MD5Encode(content: String):String{
        return EncryptUtil.MD5Encode(content = content)
    }
}