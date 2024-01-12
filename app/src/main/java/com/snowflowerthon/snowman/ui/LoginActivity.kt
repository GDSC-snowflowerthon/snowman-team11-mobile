package com.snowflowerthon.snowman.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snowflowerthon.snowman.R
import com.kakao.sdk.common.KakaoSdk
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding.inflate
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KakaoSdk.init(this, "69a3cfd1a816a40b18dc20cd4b8a300d")
        lateinit var kakaoCallback: (OAuthToken?, Throwable?) -> Unit
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val iv_app_logo = binding.ivAppLogo
        val byn_kakaoLogin = binding.btnKakaoLogin

        val context = this

        byn_kakaoLogin.setOnClickListener{
            handleButtonClick()
            Log.d("post", "버튼 클릭")
            lifecycleScope.launch {
                try {
                    // 서비스 코드에서는 간단하게 로그인 요청하고 oAuthToken 을 받아올 수 있다.
                    val oAuthToken = UserApiClient.loginWithKakao(context)
                    Log.d("MainActivity", "beanbean > $oAuthToken")

                } catch (error: Throwable) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        Log.d("MainActivity", "사용자가 명시적으로 취소")
                    } else {
                        Log.e("MainActivity", "인증 에러 발생", error)
                    }
                }
            }
        }
        }


    }

    private fun handleButtonClick() {
        //클릭 이벤트에 대한 처리
    }


