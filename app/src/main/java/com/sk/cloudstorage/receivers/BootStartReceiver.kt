package com.sk.cloudstorage.receivers

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sk.cloudstorage.features.main.MainActivity

/**
 * 开机启动通知接受
 */
class BootStartReceiver : BroadcastReceiver() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val bootIntent = Intent(
                context, MainActivity::class.java
            )
            //重启应用，得使用PendingIntent
            val restartIntent =
                PendingIntent.getActivity(context, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            val mAlarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            mAlarmManager.set(
                AlarmManager.RTC, System.currentTimeMillis() + 2000,
                restartIntent
            )
        }
    }
}