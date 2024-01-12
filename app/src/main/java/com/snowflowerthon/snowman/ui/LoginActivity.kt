package com.snowflowerthon.snowman.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snowflowerthon.snowman.R
import com.kakao.sdk.common.KakaoSdk
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding
import android.util.Log
import com.kakao.sdk.common.util.Utility
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding.inflate


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KakaoSdk.init(this, "69a3cfd1a816a40b18dc20cd4b8a300d")
        binding = inflate(layoutInflater)
        setContentView(binding.root)


        val iv_app_logo = binding.ivAppLogo
        val byn_kakaoLogin = binding.btnKakaoLogin

        byn_kakaoLogin.setOnClickListener{
            handleButtonClick()
        }


    }

    private fun handleButtonClick() {
        //클릭 이벤트에 대한 처리
    }
}
