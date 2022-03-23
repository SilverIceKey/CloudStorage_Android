package com.sk.cloudstorage.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import com.sk.cloudstorage.R
import com.sk.cloudstorage.base.BaseView
import com.sk.cloudstorage.constants.MMKVConstants
import com.tencent.mmkv.MMKV

/**
 * 登录界面
 */
class LoginView(presenter: LoginPresenter) : BaseView<LoginPresenter>(presenter) {
    //用户名
    val username: MutableState<String> by lazy {
        mutableStateOf(MMKV.defaultMMKV().getString(MMKVConstants.USERNAME, "").toString())
    }

    //密码
    val password: MutableState<String> by lazy {
        mutableStateOf(MMKV.defaultMMKV().getString(MMKVConstants.PASSWORD, "").toString())
    }

    @Preview
    @Composable
    override fun Content() {
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.app_logo),
                contentDescription = "logo"
            )
            Text(text = "云存储")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = username.value,
                placeholder = { Text(text = "请输入用户名") },
                onValueChange = {
                    username.value = it
                })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = password.value,
                placeholder = { Text(text = "请输入密码") },
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    password.value = it
                })
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier.size(120.dp, 40.dp),
                onClick = {
                    if (username.value.isEmpty()) {
                        TipDialog.show("请输入用户名", WaitDialog.TYPE.ERROR)
                        return@Button
                    }
                    if (password.value.isEmpty()) {
                        TipDialog.show("请输入密码", WaitDialog.TYPE.ERROR)
                        return@Button
                    }
                    presenter.login(username.value, password.value)
                }) {
                Text(text = "登录")
            }
        }
    }
}