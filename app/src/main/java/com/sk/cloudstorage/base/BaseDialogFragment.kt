package com.sk.cloudstorage.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 * 弹窗基类
 * */
abstract class BaseDialogFragment : DialogFragment() {
    /**
     * 获取界面id
     */
    abstract fun getLayoutID(): Int

    /**
     * 界面初始化
     */
    abstract fun initView(view: View)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //背景透明化并且设置不可点击
        if (getDialog()?.getWindow() != null) {
            getDialog()?.setCanceledOnTouchOutside(false);
        }
        val view = LayoutInflater.from(context).inflate(getLayoutID(), null)
        initView(view)
        return view
    }
}