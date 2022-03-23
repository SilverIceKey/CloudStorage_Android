package com.sk.cloudstorage.features.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sk.cloudstorage.base.BaseView

class SplashView(presenter: SplashPresenter) : BaseView<SplashPresenter>(presenter) {


    @Composable
    override fun Content(){
        Column {
            Text(text = "正在初始化程序")
        }
    }
}