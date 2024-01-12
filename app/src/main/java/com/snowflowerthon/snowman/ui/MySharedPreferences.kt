package com.snowflowerthon.snowman.ui

import android.content.Context
import android.content.SharedPreferences
import com.kakao.sdk.auth.model.OAuthToken

object MySharedPreferences {

    private val MY_ACCOUNT: String = "account"

    fun setProviderId(context: Context, input: Long?) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PROVIDERID", input.toString())
        editor.commit()
    }

    fun getProviderId(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("MY_PROVIDERID", "").toString()
    }

    fun setToken(context: Context, accessToken: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_TOKEN", accessToken)
        editor.commit()
    }

    fun getToken(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val token = prefs.getString("MY_TOKEN", "").toString()
        return if (token.isNotEmpty()) {
            "Bearer $token"
        } else {
            ""
        }
    }

}