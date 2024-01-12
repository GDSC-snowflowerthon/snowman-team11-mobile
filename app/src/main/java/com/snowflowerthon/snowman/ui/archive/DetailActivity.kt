package com.snowflowerthon.snowman.ui.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewFlipper
import com.snowflowerthon.snowman.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewFlipper = findViewById<ViewFlipper>(R.id.viewFlipper)

        // 클릭 이벤트 처리
        viewFlipper.setOnClickListener {
            // 카드 뒤집기
            viewFlipper.showNext()
        }
    }
}