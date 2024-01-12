package com.snowflowerthon.snowman.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.snowflowerthon.snowman.data.ApiService
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.dto.request.LoginRequsetDto
import com.snowflowerthon.snowman.databinding.ActivityLoginBinding
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

        // SharedPreferences 안에 값이 저장되어 있을 때-> Login 패스하기
        if (MySharedPreferences.getProviderId(this).isNotBlank()) {
            // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity로 이동
            Toast.makeText(
                this,
                "자동 로그인 되었습니다.",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val context = this


        binding.btnKakaoLogin.setOnClickListener {
            Log.d("post", "버튼 클릭")
            lifecycleScope.launch {
                try {
                    // 서비스 코드에서는 간단하게 로그인 요청하고 oAuthToken 을 받아올 수 있다.
                    val oAuthToken = UserApiClient.loginWithKakao(context)
                    Log.d("MainActivity", "beanbean > $oAuthToken")
                    postUserInfo()

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


    private fun postUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (user != null) {
                // 유저의 아이디. 프로바이더아이디 받아옴
                Log.d(TAG, "invoke: id =" + user.id)
                val providerID = user.id.toString()
                MySharedPreferences.setProviderId(this@LoginActivity, providerID)

                val intent = Intent(this, MainActivity::class.java)

                val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)

            }

            val providerId = MySharedPreferences.getProviderId(this@LoginActivity).toLong()
            val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)
            retrofitAPI.loginKakao(LoginRequsetDto(providerId))
        }
    }
}
