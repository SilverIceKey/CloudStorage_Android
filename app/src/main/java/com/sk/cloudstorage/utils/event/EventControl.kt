package com.sk.cloudstorage.utils.event

import com.sk.cloudstorage.utils.log.CommonLogUtil
import org.greenrobot.eventbus.EventBus

/**
 * EventBus消息推送
 */
object EventControl {
    /**
     * 第二屏更新
     */
    fun updateSec() {
        CommonLogUtil.log.info("发送消息:updateSec")
        val eventBean = EventBean<String>()
        eventBean.type = "updateSec"
        EventBus.getDefault().post(eventBean)
    }

    /**
     * 屏幕开启通知
     */
    fun screenOn() {
        CommonLogUtil.log.info("发送消息:screenOn")
        val eventBean = EventBean<String>()
        eventBean.type = "screenOn"
        EventBus.getDefault().post(eventBean)
    }

    /**
     * 屏幕关闭通知
     */
    fun screenOff() {
        CommonLogUtil.log.info("发送消息:screenOff")
        val eventBean = EventBean<String>()
        eventBean.type = "screenOff"
        EventBus.getDefault().post(eventBean)
    }

    /**
     * 显示二维码
     */
    fun qrcodeShow(qrcode: String) {
        CommonLogUtil.log.info("发送消息:qrcodeShow")
        val eventBean = EventBean<String>()
        eventBean.type = "qrcodeShow"
        eventBean.data = qrcode
        EventBus.getDefault().post(eventBean)
    }
    /**
     * 显示二维码
     */
    fun qrcodeHide() {
        CommonLogUtil.log.info("发送消息:qrcodeHide")
        val eventBean = EventBean<String>()
        eventBean.type = "qrcodeHide"
        EventBus.getDefault().post(eventBean)
    }

    /**
     * 刷新详情页面
     */
    fun updateRoomDetail(){
        CommonLogUtil.log.info("发送消息:updateRoomDetail")
        val eventBean = EventBean<String>()
        eventBean.type = "updateRoomDetail"
        EventBus.getDefault().post(eventBean)
    }
    /**
     * 刷新主屏幕信息
     */
    fun refreshRoomData(){
        CommonLogUtil.log.info("发送消息:refreshRoomData")
        val eventBean = EventBean<String>()
        eventBean.type = "refreshRoomData"
        EventBus.getDefault().post(eventBean)
    }
}