package com.snowflowerthon.snowman.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snowflowerthon.snowman.R
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding.inflate


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        KakaoSdk.init(this, "69a3cfd1a816a40b18dc20cd4b8a300d")


    }
}
