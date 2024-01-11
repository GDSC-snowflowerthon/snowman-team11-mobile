package com.snowflowerthon.snowman.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD:app/src/main/java/com/snowflowerthon/snowman/LoginActivity.kt
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
=======
import com.snowflowerthon.snowman.R
>>>>>>> 42080928659506ee9c3b139ce7fe7460b99f8c1d:app/src/main/java/com/snowflowerthon/snowman/ui/LoginActivity.kt

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KakaoSdk.init(this, "69a3cfd1a816a40b18dc20cd4b8a300d")
        setContentView(R.layout.activity_login)





    }
}
