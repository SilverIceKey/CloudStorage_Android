package com.sk.cloudstorage.features.main

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sk.cloudstorage.base.BaseView

class MainView() : BaseView() {
    val texts: SnapshotStateList<String> = mutableStateListOf()
    fun initTexts() {
        texts.clear()
        for (index in 0..1) {
            log.info("${index}")
            texts.add("${index}")
        }
        log.info("${texts.size}")
    }

    @ExperimentalAnimationApi
    @Preview(name = "MainParentView")
    @Composable
    override fun Content() {
        var state by remember { mutableStateOf(true) }
        var isInit by remember { mutableStateOf(false) }
        var urlPainter = rememberImagePainter(
            data =
            "https://i1.hdslb.com/bfs/archive/d73acdbca3a862e8d43fa14aebf954f81626955a.jpg"
        )
        if (!isInit) {
            initTexts()
            isInit = true
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = state) {
                Text(
                    text = "这是一个普通的正文",
                    fontWeight = FontWeight.W900,
                    style = MaterialTheme.typography.h5
                )
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = { state = !state }) {
                Text(if (state) "隐藏" else "显示")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                texts.add("${texts.size}")
            }) {
                Text(text = "新增数字")
            }
            Image(
                painter =urlPainter,
                contentDescription = "图片",
                modifier = Modifier
                    .size(128.dp,100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                for (text in texts) {
                    val visible = remember {
                        mutableStateOf(false)
                    }
                    AnimatedVisibility(visible = visible.value) {
                        Text(
                            text = text,
                            fontWeight = FontWeight.W900,
                            style = MaterialTheme.typography.h5
                        )
                    }
                    if (!visible.value){
                        visible.value = true
                    }
                }
            }
        }
    }
}