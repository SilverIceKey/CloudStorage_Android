package com.sk.cloudstorage.features.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sk.cloudstorage.R
import com.sk.cloudstorage.base.BaseView

class MainView(presenter: MainPresenter) : BaseView<MainPresenter>(presenter) {
    val isTopDir by lazy {
        mutableStateOf(true)
    }

    @ExperimentalAnimationApi
    @Preview
    @Composable
    override fun Content() {
        Column(Modifier.fillMaxSize()) {
            headView()
            Divider(color = Color.Black)
        }
    }

    @ExperimentalAnimationApi
    @Preview
    @Composable
    fun headView(){
        Scaffold(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                visible = !isTopDir.value
            ) {
                IconButton(
                    onClick = {

                    },
                ) {
                    Icon(
                        painter = rememberImagePainter(data = R.drawable.icon_back),
                        contentDescription = "返回"
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "首页"
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
            )
            TextButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .wrapContentHeight(Alignment.CenterVertically),
                onClick = {
                    presenter.logout()
                }) {
                Text(text = "退出",color = Color.Black)
            }
        }
    }
}